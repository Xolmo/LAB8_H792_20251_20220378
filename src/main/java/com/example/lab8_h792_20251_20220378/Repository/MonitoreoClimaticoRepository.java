package com.example.lab8_h792_20251_20220378.Repository;

import com.example.lab8_h792_20251_20220378.Entity.MonitoreoClimatico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoreoClimaticoRepository extends JpaRepository<MonitoreoClimatico, Integer> {
}
