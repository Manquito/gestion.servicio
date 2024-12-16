package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Profesor;
import com.ejemplo.gestion.servicio.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping
    public List<Profesor> obtenerTodos() {
        return profesorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Profesor obtenerPorId(@PathVariable Long id) {
        return profesorRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profesor crear(@RequestBody Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @PutMapping("/{id}")
    public Profesor actualizar(@PathVariable Long id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        return profesorRepository.save(profesor);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        profesorRepository.deleteById(id);
    }
}