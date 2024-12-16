package com.ejemplo.gestion.servicio.repository;

import com.ejemplo.gestion.servicio.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
}