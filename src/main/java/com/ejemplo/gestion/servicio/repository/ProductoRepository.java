package com.ejemplo.gestion.servicio.repository;

import com.ejemplo.gestion.servicio.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
