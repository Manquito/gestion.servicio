package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.dto.CompraRequest;
import com.ejemplo.gestion.servicio.model.Cliente;
import com.ejemplo.gestion.servicio.model.Producto;
import com.ejemplo.gestion.servicio.model.Compra;
import com.ejemplo.gestion.servicio.repository.ClienteRepository;
import com.ejemplo.gestion.servicio.repository.ProductoRepository;
import com.ejemplo.gestion.servicio.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Crear una nueva compra (POST)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Compra crearCompra(@RequestBody CompraRequest request) {
        // Obtener el cliente por ID
        Cliente cliente = clienteRepository.findById(request.getClienteId()).orElse(null);
        // Obtener el producto por ID
        Producto producto = productoRepository.findById(request.getProductoId()).orElse(null);

        // Verificar si el cliente y el producto existen
        if (cliente != null && producto != null) {
            // Verificar si hay suficiente stock
            if (producto.getStock() >= request.getCantidad()) {
                // Crear la compra
                Compra compra = new Compra();
                compra.setCliente(cliente); // Asignar el cliente a la compra
                compra.setProducto(producto); // Asignar el producto a la compra
                compra.setCantidad(request.getCantidad()); // Asignar la cantidad
                compra.setFechaCompra(new Date()); // Asignar la fecha de la compra

                // Reducir el stock del producto
                producto.setStock(producto.getStock() - request.getCantidad());
                productoRepository.save(producto); // Guardar el producto con el nuevo stock

                // Guardar la compra en la base de datos
                return compraRepository.save(compra);
            } else {
                throw new RuntimeException("No hay suficiente stock disponible.");
            }
        } else {
            throw new RuntimeException("Cliente o Producto no encontrado.");
        }
    }

    // Obtener todas las compras (GET)
    @GetMapping
    public List<Compra> obtenerTodasLasCompras() {
        return compraRepository.findAll(); // Retorna todas las compras
    }

    // Obtener una compra por ID (GET)
    @GetMapping("/{id}")
    public Compra obtenerCompraPorId(@PathVariable Long id) {
        Optional<Compra> compra = compraRepository.findById(id);
        if (compra.isPresent()) {
            return compra.get(); // Si existe la compra, devolverla
        } else {
            throw new RuntimeException("Compra no encontrada."); // Si no existe, lanzar error
        }
    }

    // Actualizar una compra existente (PUT)
    @PutMapping("/{id}")
    public Compra actualizarCompra(@PathVariable Long id, @RequestBody CompraRequest request) {
        Optional<Compra> compraExistente = compraRepository.findById(id);

        if (compraExistente.isPresent()) {
            Compra compra = compraExistente.get();

            // Obtener el cliente y producto por sus ID
            Cliente cliente = clienteRepository.findById(request.getClienteId()).orElse(null);
            Producto producto = productoRepository.findById(request.getProductoId()).orElse(null);

            // Verificar si el cliente y el producto existen
            if (cliente != null && producto != null) {
                // Verificar si hay suficiente stock
                if (producto.getStock() >= request.getCantidad()) {
                    // Actualizar los datos de la compra
                    compra.setCliente(cliente);
                    compra.setProducto(producto);
                    compra.setCantidad(request.getCantidad());

                    // Reducir el stock del producto
                    producto.setStock(producto.getStock() - request.getCantidad());
                    productoRepository.save(producto); // Guardar el producto con el nuevo stock

                    // Guardar la compra actualizada
                    return compraRepository.save(compra);
                } else {
                    throw new RuntimeException("No hay suficiente stock disponible.");
                }
            } else {
                throw new RuntimeException("Cliente o Producto no encontrado.");
            }
        } else {
            throw new RuntimeException("Compra no encontrada.");
        }
    }

    // Eliminar una compra (DELETE)
    @DeleteMapping("/{id}")
    public void eliminarCompra(@PathVariable Long id) {
        Optional<Compra> compraExistente = compraRepository.findById(id);
        if (compraExistente.isPresent()) {
            // Eliminar la compra
            compraRepository.deleteById(id);
        } else {
            throw new RuntimeException("Compra no encontrada.");
        }
    }
}
