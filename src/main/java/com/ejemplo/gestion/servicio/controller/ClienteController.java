package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Cliente;
import com.ejemplo.gestion.servicio.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> obtenerTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        logger.info("GET /clientes: Total de clientes encontrados: " + clientes.size());
        return clientes;
    }

    @GetMapping("/{id}")
    public Cliente obtenerPorId(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            logger.info("GET /clientes/{}: Cliente encontrado: {}", id, cliente);
        } else {
            logger.warn("GET /clientes/{}: Cliente no encontrado", id);
        }
        return cliente;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente crear(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteRepository.save(cliente);
        logger.info("POST /clientes: Cliente creado: {}", nuevoCliente);
        return nuevoCliente;
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id);
            Cliente clienteActualizado = clienteRepository.save(cliente);
            logger.info("PUT /clientes/{}: Cliente actualizado: {}", id, clienteActualizado);
            return clienteActualizado;
        } else {
            logger.warn("PUT /clientes/{}: Cliente no encontrado para actualizar", id);
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            logger.info("DELETE /clientes/{}: Cliente eliminado", id);
        } else {
            logger.warn("DELETE /clientes/{}: Cliente no encontrado para eliminar", id);
        }
    }
}
