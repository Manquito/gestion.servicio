package com.ejemplo.gestion.servicio.repository;

import com.ejemplo.gestion.servicio.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}