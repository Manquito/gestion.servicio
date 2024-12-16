package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Categoria;
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
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerTodasLasCategorias() throws Exception {
        mockMvc.perform(get("/categorias"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    List<Categoria> categorias = List.of(objectMapper.readValue(jsonResponse, Categoria[].class));
                    System.out.println("Categorías obtenidas:");
                    categorias.forEach(categoria -> System.out.println("Nombre: " + categoria.getNombre()));
                });
    }

    @Test
    void testObtenerCategoriaPorId() throws Exception {
        mockMvc.perform(get("/categorias/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nombre").value("Muebles"))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    Categoria categoria = objectMapper.readValue(jsonResponse, Categoria.class);
                    System.out.println("Categoría obtenida:");
                    System.out.println("Nombre: " + categoria.getNombre());
                });
    }

    @Test
    void testCrearCategoria() throws Exception {
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre("Deportes");

        mockMvc.perform(post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevaCategoria)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nombre").value("Deportes"))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    Categoria categoriaCreada = objectMapper.readValue(jsonResponse, Categoria.class);
                    System.out.println("Categoría creada:");
                    System.out.println("Nombre: " + categoriaCreada.getNombre());
                });
    }

    @Test
    void testActualizarCategoria() throws Exception {
        Categoria categoriaActualizada = new Categoria();
        categoriaActualizada.setNombre("Electrónica Avanzada");

        mockMvc.perform(put("/categorias/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoriaActualizada)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Electrónica Avanzada"))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    Categoria categoria = objectMapper.readValue(jsonResponse, Categoria.class);
                    System.out.println("Categoría actualizada:");
                    System.out.println("Nombre: " + categoria.getNombre());
                });
    }

    @Test
    void testEliminarCategoria() throws Exception {
        mockMvc.perform(delete("/categorias/{id}", 5))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println("Categoría con ID 5 eliminada exitosamente."));
    }
}
