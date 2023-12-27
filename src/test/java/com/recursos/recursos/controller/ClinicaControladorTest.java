package com.recursos.recursos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recursos.recursos.entity.Clinica;
import com.recursos.recursos.entity.Medico;
import com.recursos.recursos.entity.Persona;
import com.recursos.recursos.services.ClinicaServicio;
import com.recursos.recursos.services.MedicoServicio;
import com.recursos.recursos.services.PersonaServicio;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ClinicaControlador.class)
class ClinicaControladorTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClinicaServicio clinicaServicio;

    @MockBean
    private PersonaServicio personaServicio;

    @MockBean
    private MedicoServicio medicoServicio;

    @Test
    public void testGuardarClinica() throws Exception {
        // Datos de prueba
        Integer medicoId = 1;
        Integer personaId = 1;
        Clinica clinica = new Clinica();
        clinica.setClinica_id(1);
        // ... Establecer otras propiedades de la clínica

        // Simular el comportamiento del servicio
        given(personaServicio.get(personaId)).willReturn(Optional.of(new Persona()));
        given(medicoServicio.get(medicoId)).willReturn(Optional.of(new Medico()));
        given(clinicaServicio.guardarprescripcion(any(Clinica.class), anyList())).willReturn(clinica);

        // Realizar la solicitud POST con el cuerpo de la clínica en formato JSON crear/{medico_id}/{persona_id}
        ResultActions response= mockMvc.perform(post("/clinica/crear/{medico_id}/{persona_id}", medicoId, personaId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clinica))); // Proporcionar el contenido JSON completo de la clínica
        response.andDo(print());

    }
}