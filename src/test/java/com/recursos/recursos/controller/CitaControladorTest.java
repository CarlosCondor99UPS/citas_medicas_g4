package com.recursos.recursos.controller;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;



import com.recursos.recursos.services.CitaServicio;
import com.recursos.recursos.services.MedicoServicio;
import com.recursos.recursos.services.PersonaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(CitaControlador.class)
public class CitaControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaServicio personaServicio;
    @MockBean
    private MedicoServicio medicoServicio;
    @MockBean
    private CitaServicio citaServicio;


    @Autowired
    private ObjectMapper objectMapper;
}
