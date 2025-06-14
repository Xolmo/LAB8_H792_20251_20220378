package com.example.lab8_h792_20251_20220378.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "monitoreo_climatico")
public class MonitoreoClimatico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ciudad;
    private LocalDate fecha;
    @Column(name = "temperatura_prom")
    private float temperaturaPromedio;
    @Column(name= "cond_climatica_frecuente")
    private String condicionFrecuente;
    @Column(name = "temperatura_max")
    private float temperaturaMaxima;
    @Column(name = "temperatura_min")
    private float temperaturaMinima;
}
