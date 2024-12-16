package com.ejemplo.gestion.servicio.repository;

import com.ejemplo.gestion.servicio.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
