package com.ejemplo.gestion.servicio.repository;

import com.ejemplo.gestion.servicio.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
