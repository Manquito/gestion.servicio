package com.ejemplo.gestion.servicio.repository;

import com.ejemplo.gestion.servicio.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}