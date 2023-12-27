package com.recursos.recursos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recursos.recursos.entity.Persona;
import com.recursos.recursos.services.PersonaServicio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PersonaControlador.class)
class PersonaControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaServicio personaServicio;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGuardarPersona() throws Exception {

        Persona persona = new Persona("0100897687", "santiago", "cabrera", "Gualaceo", "0998087966", "ssc@gmail.com",
                "activo", "17-02-2002", "masculino", "Empleado");

        given(personaServicio.crearPersona(any(Persona.class))).willReturn(persona);

        ResultActions response = mockMvc.perform(post("/personas/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(persona)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is(persona.getNombre())))
                .andExpect(jsonPath("$.apellido", is(persona.getApellido())))
                .andExpect(jsonPath("$.correo", is(persona.getCorreo())));
    }
    @Test
    public void testBuscarPersonaPorId() throws Exception {
        // ID de la persona a buscar
        int id = 1;

        // Crea una persona de ejemplo
        Persona persona = new Persona("0100897687", "santiago", "cabrera", "Gualaceo", "0998087966", "ssc@gmail.com",
                "activo", "17-02-2002", "masculino", "Empleado");
        persona.setPersona_id(id); // Establece el ID de la persona

        given(personaServicio.obtenerPersonaPorId(id)).willReturn(persona); // Simula la respuesta del servicio

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/personas/{persona_id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persona_id", is(persona.getPersona_id())))
                .andExpect(jsonPath("$.nombre", is(persona.getNombre())))
                .andExpect(jsonPath("$.apellido", is(persona.getApellido())))
                .andExpect(jsonPath("$.correo", is(persona.getCorreo())));
    }


    @Test
    public void testEliminarPersonaPorId() throws Exception {
        // ID de la persona a eliminar
        int id = 1;

        // Simula la eliminación exitosa de la persona sin devolver ningún valor
        doNothing().when(personaServicio).eliminarPersona(id);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/personas/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}