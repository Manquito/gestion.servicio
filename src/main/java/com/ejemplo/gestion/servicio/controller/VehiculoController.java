package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Vehiculo;
import com.ejemplo.gestion.servicio.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping
    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vehiculo obtenerPorId(@PathVariable Long id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehiculo crear(@RequestBody Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @PutMapping("/{id}")
    public Vehiculo actualizar(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        vehiculo.setId(id);
        return vehiculoRepository.save(vehiculo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        vehiculoRepository.deleteById(id);
    }
}