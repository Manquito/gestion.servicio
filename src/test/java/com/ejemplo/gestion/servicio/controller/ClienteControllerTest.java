package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerTodosLosClientes() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    List<Cliente> clientes = List.of(objectMapper.readValue(jsonResponse, Cliente[].class));
                    System.out.println("Clientes obtenidos:");
                    clientes.forEach(cliente -> System.out.println("Nombre: " + cliente.getNombre()));
                });
    }

    @Test
    void testObtenerClientePorId() throws Exception {
        mockMvc.perform(get("/clientes/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nombre").value("Sofía Martínez"))
                .andExpect(jsonPath("$.email").value("sofia.martinez@example.com"))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    Cliente cliente = objectMapper.readValue(jsonResponse, Cliente.class);
                    System.out.println("Cliente obtenido:");
                    System.out.println("Nombre: " + cliente.getNombre());
                });
    }

    @Test
    void testCrearCliente() throws Exception {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre("Julia Fernández");
        nuevoCliente.setEmail("julia.fernandez@example.com");

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevoCliente)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nombre").value("Julia Fernández"))
                .andExpect(jsonPath("$.email").value("julia.fernandez@example.com"))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    Cliente clienteCreado = objectMapper.readValue(jsonResponse, Cliente.class);
                    System.out.println("Cliente creado:");
                    System.out.println("Nombre: " + clienteCreado.getNombre());
                });
    }

    @Test
    void testActualizarCliente() throws Exception {
        Cliente clienteActualizado = new Cliente();
        clienteActualizado.setNombre("Liam Anderson Modificado");
        clienteActualizado.setEmail("liam.anderson.modificado@example.com");

        mockMvc.perform(put("/clientes/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteActualizado)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Liam Anderson Modificado"))
                .andExpect(jsonPath("$.email").value("liam.anderson.modificado@example.com"))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    Cliente cliente = objectMapper.readValue(jsonResponse, Cliente.class);
                    System.out.println("Cliente actualizado:");
                    System.out.println("Nombre: " + cliente.getNombre());
                });
    }

    @Test
    void testEliminarCliente() throws Exception {
        mockMvc.perform(delete("/clientes/{id}", 5))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println("Cliente con ID 5 eliminado exitosamente."));
    }
}
