package com.ejemplo.gestion.servicio.repository;

import com.ejemplo.gestion.servicio.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
