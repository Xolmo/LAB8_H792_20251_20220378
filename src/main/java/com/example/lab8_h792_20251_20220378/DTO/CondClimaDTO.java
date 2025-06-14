package com.example.lab8_h792_20251_20220378.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CondClimaDTO {
    private float temperaturaActual;
    private String condicionClimatica;
    private float sensacionTermica;
    private int humedad;
}
