package com.ejemplo.gestion.servicio.repository;

import com.ejemplo.gestion.servicio.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}