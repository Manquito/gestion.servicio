package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Mascota;
import com.ejemplo.gestion.servicio.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @GetMapping
    public List<Mascota> obtenerTodas() {
        return mascotaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mascota obtenerPorId(@PathVariable Long id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mascota crear(@RequestBody Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @PutMapping("/{id}")
    public Mascota actualizar(@PathVariable Long id, @RequestBody Mascota mascota) {
        mascota.setId(id);
        return mascotaRepository.save(mascota);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        mascotaRepository.deleteById(id);
    }
}