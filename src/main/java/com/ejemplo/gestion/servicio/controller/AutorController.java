package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Autor;
import com.ejemplo.gestion.servicio.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public List<Autor> obtenerTodos() {
        return autorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Autor obtenerPorId(@PathVariable Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autor crear(@RequestBody Autor autor) {
        return autorRepository.save(autor);
    }

    @PutMapping("/{id}")
    public Autor actualizar(@PathVariable Long id, @RequestBody Autor autor) {
        autor.setId(id);
        return autorRepository.save(autor);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        autorRepository.deleteById(id);
    }
}