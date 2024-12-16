package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Producto;
import com.ejemplo.gestion.servicio.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }

    // Nuevo endpoint para actualizar el stock
    @PutMapping("/{id}/actualizarStock")
    public Producto actualizarStock(@PathVariable Long id, @RequestParam int cantidad) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            if (producto.getStock() >= cantidad) {
                producto.setStock(producto.getStock() - cantidad);  // Reducir el stock disponible
                return productoRepository.save(producto);  // Guardar los cambios
            } else {
                throw new RuntimeException("No hay suficiente stock disponible.");
            }
        } else {
            throw new RuntimeException("Producto no encontrado.");
        }
    }
}
