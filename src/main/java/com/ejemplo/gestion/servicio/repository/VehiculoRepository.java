package com.ejemplo.gestion.servicio.repository;

import com.ejemplo.gestion.servicio.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}