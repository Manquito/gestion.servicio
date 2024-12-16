package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Producto;
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
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerTodosLosProductos() throws Exception {
        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    List<Producto> productos = List.of(objectMapper.readValue(jsonResponse, Producto[].class));
                    System.out.println("Productos obtenidos:");
                    productos.forEach(producto -> System.out.println("Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio()));
                });
    }

    @Test
    void testObtenerProductoPorId() throws Exception {
        mockMvc.perform(get("/productos/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nombre").value("Sofá 3 plazas"))
                .andExpect(jsonPath("$.precio").value(450.5))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    Producto producto = objectMapper.readValue(jsonResponse, Producto.class);
                    System.out.println("Producto obtenido:");
                    System.out.println("Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio());
                });
    }

    @Test
    void testCrearProducto() throws Exception {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Teléfono Samsung Galaxy S21");
        nuevoProducto.setPrecio(899.99);

        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevoProducto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nombre").value("Teléfono Samsung Galaxy S21"))
                .andExpect(jsonPath("$.precio").value(899.99))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    Producto productoCreado = objectMapper.readValue(jsonResponse, Producto.class);
                    System.out.println("Producto creado:");
                    System.out.println("Nombre: " + productoCreado.getNombre() + ", Precio: " + productoCreado.getPrecio());
                });
    }

    @Test
    void testActualizarProducto() throws Exception {
        Producto productoActualizado = new Producto();
        productoActualizado.setNombre("Laptop Dell XPS 13 - 2022");
        productoActualizado.setPrecio(1300.0);

        mockMvc.perform(put("/productos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoActualizado)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Laptop Dell XPS 13 - 2022"))
                .andExpect(jsonPath("$.precio").value(1300.0))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    Producto producto = objectMapper.readValue(jsonResponse, Producto.class);
                    System.out.println("Producto actualizado:");
                    System.out.println("Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio());
                });
    }

    @Test
    void testEliminarProducto() throws Exception {
        mockMvc.perform(delete("/productos/{id}", 5))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println("Producto con ID 5 eliminado exitosamente."));
    }
}
