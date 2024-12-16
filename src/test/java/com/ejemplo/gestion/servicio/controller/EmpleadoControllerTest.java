package com.ejemplo.gestion.servicio.controller;

import com.ejemplo.gestion.servicio.model.Empleado;
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
public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerTodosLosEmpleados() throws Exception {
        mockMvc.perform(get("/empleados"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    List<Empleado> empleados = List.of(objectMapper.readValue(jsonResponse, Empleado[].class));
                    System.out.println("Empleados obtenidos:");
                    empleados.forEach(empleado -> System.out.println("Nombre: " + empleado.getNombre()));
                });
    }

    @Test
    void testObtenerEmpleadoPorId() throws Exception {
        mockMvc.perform(get("/empleados/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nombre").value("Ana Gómez"))
                .andExpect(jsonPath("$.correo").value("ana.gomez@example.com"))
                .andExpect(jsonPath("$.puesto").value("Desarrolladora"))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    Empleado empleado = objectMapper.readValue(jsonResponse, Empleado.class);
                    System.out.println("Empleado obtenido:");
                    System.out.println("Nombre: " + empleado.getNombre());
                });
    }

    @Test
    void testCrearEmpleado() throws Exception {
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombre("Ricardo Fernández");
        nuevoEmpleado.setCorreo("ricardo.fernandez@example.com");
        nuevoEmpleado.setPuesto("Manager");

        mockMvc.perform(post("/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevoEmpleado)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nombre").value("Ricardo Fernández"))
                .andExpect(jsonPath("$.correo").value("ricardo.fernandez@example.com"))
                .andExpect(jsonPath("$.puesto").value("Manager"))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    Empleado empleadoCreado = objectMapper.readValue(jsonResponse, Empleado.class);
                    System.out.println("Empleado creado:");
                    System.out.println("Nombre: " + empleadoCreado.getNombre());
                });
    }

    @Test
    void testActualizarEmpleado() throws Exception {
        Empleado empleadoActualizado = new Empleado();
        empleadoActualizado.setNombre("Carlos Rodríguez Modificado");
        empleadoActualizado.setCorreo("carlos.rodriguez.modificado@example.com");
        empleadoActualizado.setPuesto("Director");

        mockMvc.perform(put("/empleados/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleadoActualizado)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Carlos Rodríguez Modificado"))
                .andExpect(jsonPath("$.correo").value("carlos.rodriguez.modificado@example.com"))
                .andExpect(jsonPath("$.puesto").value("Director"))
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    Empleado empleado = objectMapper.readValue(jsonResponse, Empleado.class);
                    System.out.println("Empleado actualizado:");
                    System.out.println("Nombre: " + empleado.getNombre());
                });
    }

    @Test
    void testEliminarEmpleado() throws Exception {
        mockMvc.perform(delete("/empleados/{id}", 5))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println("Empleado con ID 5 eliminado exitosamente."));
    }
}
