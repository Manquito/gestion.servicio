package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Empleado;
import com.ejemplo.gestion.servicio.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado crear(@RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        empleado.setId(id);
        return empleadoRepository.save(empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        empleadoRepository.deleteById(id);
    }
}
