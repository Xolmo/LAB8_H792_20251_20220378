package com.example.lab8_h792_20251_20220378.Services;

import com.example.lab8_h792_20251_20220378.DTO.CondClimaDTO;
import com.example.lab8_h792_20251_20220378.DTO.PronosticoHoraDTO;
import com.example.lab8_h792_20251_20220378.Entity.MonitoreoClimatico;
import com.example.lab8_h792_20251_20220378.Repository.MonitoreoClimaticoRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.*;

@Service
public class ClimaService {

    @Autowired
    MonitoreoClimaticoRepository repo;

    final String key = "52ffccd48ed8443f88f12019251406";

    public CondClimaDTO obtnerCondClima(String ciudad){
        String url = "https://api.weatherapi.com/v1/current.json?key=" + key + "&q=" + ciudad;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(response);
        JSONObject current = json.optJSONObject("current");
        JSONObject condition = current.optJSONObject("condition");

        float tempC = current.getFloat("temp_c");
        String condicion = condition.optString("text");
        float feelslikeC = current.getFloat("feelslike_c");
        int humedad = current.getInt("humidity");

        CondClimaDTO answer = new CondClimaDTO();
        answer.setCondicionClimatica(condicion);
        answer.setHumedad(humedad);
        answer.setTemperaturaActual(tempC);
        answer.setSensacionTermica(feelslikeC);

        return answer;
    }

    public List<PronosticoHoraDTO> obtenerPronosticoPorHora(String ciudad) {
        String url = "https://api.weatherapi.com/v1/forecast.json?key=" + key + "&q=" + ciudad + "&days=1";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);

        JSONArray horas = json.getJSONObject("forecast")
                .getJSONArray("forecastday")
                .getJSONObject(0)
                .getJSONArray("hour");

        List<PronosticoHoraDTO> pronosticoPorHora = new ArrayList<>();

        for (int i = 0; i < horas.length(); i++) {
            JSONObject horaObj = horas.getJSONObject(i);
            String hora = horaObj.getString("time");
            float temperatura = horaObj.getFloat("temp_c");
            String condicion = horaObj.getJSONObject("condition").getString("text");

            PronosticoHoraDTO answer = new PronosticoHoraDTO();
            answer.setTemperatura(temperatura);
            answer.setCondicion(condicion);
            answer.setHora(hora);
            pronosticoPorHora.add(answer);
        }

        return pronosticoPorHora;
    }

    public MonitoreoClimatico registrarMonitoreo(String ciudad) {
        String url = "https://api.weatherapi.com/v1/forecast.json?key=" + key + "&q=" + ciudad + "&days=1";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);

        JSONObject forecastDay = json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0);
        JSONArray horas = forecastDay.getJSONArray("hour");

        float sumaTemperaturas = 0;
        float maxTemp = Float.MIN_VALUE;
        float minTemp = Float.MAX_VALUE;

        Map<String, Integer> contadorCondiciones = new HashMap<>();

        for (int i = 0; i < horas.length(); i++) {
            JSONObject hora = horas.getJSONObject(i);
            float temp = hora.getFloat("temp_c");
            String condicion = hora.getJSONObject("condition").getString("text");

            sumaTemperaturas += temp;
            maxTemp = Math.max(maxTemp, temp);
            minTemp = Math.min(minTemp, temp);
            contadorCondiciones.put(condicion, contadorCondiciones.getOrDefault(condicion, 0) + 1);
        }

        String condicionFrecuente = Collections.max(contadorCondiciones.entrySet(), Map.Entry.comparingByValue()).getKey();

        MonitoreoClimatico registro = new MonitoreoClimatico();
        registro.setCiudad(ciudad);
        registro.setFecha(LocalDate.parse(forecastDay.getString("date")));
        registro.setTemperaturaPromedio(sumaTemperaturas / horas.length());
        registro.setTemperaturaMaxima(maxTemp);
        registro.setTemperaturaMinima(minTemp);
        registro.setCondicionFrecuente(condicionFrecuente);

        return repo.save(registro);
    }


}
