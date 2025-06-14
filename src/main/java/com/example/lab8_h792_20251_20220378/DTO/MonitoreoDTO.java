package com.example.lab8_h792_20251_20220378.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MonitoreoDTO {
    private String ciudad;
    private LocalDate fecha;
    private float temperaturaPromedio;
    private String condicionFrecuente;
    private float temperaturaMaxima;
    private float temperaturaMinima;
}
