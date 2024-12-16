package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Libro;
import com.ejemplo.gestion.servicio.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Libro crear(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @RequestBody Libro libro) {
        libro.setId(id);
        return libroRepository.save(libro);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        libroRepository.deleteById(id);
    }
}