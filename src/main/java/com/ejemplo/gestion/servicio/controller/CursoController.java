package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Curso;
import com.ejemplo.gestion.servicio.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Curso obtenerPorId(@PathVariable Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Curso crear(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    @PutMapping("/{id}")
    public Curso actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        return cursoRepository.save(curso);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cursoRepository.deleteById(id);
    }
}