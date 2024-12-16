package com.ejemplo.gestion.servicio.repository;

import com.ejemplo.gestion.servicio.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}