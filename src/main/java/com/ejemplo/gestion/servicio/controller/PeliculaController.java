package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Pelicula;
import com.ejemplo.gestion.servicio.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @GetMapping
    public List<Pelicula> obtenerTodas() {
        return peliculaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPorId(@PathVariable Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pelicula crear(@RequestBody Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @PutMapping("/{id}")
    public Pelicula actualizar(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        pelicula.setId(id);
        return peliculaRepository.save(pelicula);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        peliculaRepository.deleteById(id);
    }
}