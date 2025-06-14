package com.example.lab8_h792_20251_20220378.Controller;

import com.example.lab8_h792_20251_20220378.DTO.CondClimaDTO;
import com.example.lab8_h792_20251_20220378.DTO.PronosticoHoraDTO;
import com.example.lab8_h792_20251_20220378.Entity.MonitoreoClimatico;
import com.example.lab8_h792_20251_20220378.Services.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebController {

    @Autowired
    private ClimaService climaService;


    @GetMapping("/actual")
    public CondClimaDTO obtenerClimaActual(@RequestParam("ciudad") String ciudad) {
        return climaService.obtnerCondClima(ciudad);
    }

    @GetMapping("/pronosticodia")
    public List<PronosticoHoraDTO> obtenerPronosticoPorHoras(@RequestParam("ciudad") String ciudad) {
        return climaService.obtenerPronosticoPorHora(ciudad);
    }

    @PostMapping("/registrar-monitoreo")
    public MonitoreoClimatico registrar(@RequestParam String ciudad) {
        return climaService.registrarMonitoreo(ciudad);
    }
}
