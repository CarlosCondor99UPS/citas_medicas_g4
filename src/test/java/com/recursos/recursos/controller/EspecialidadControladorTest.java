package com.recursos.recursos.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import com.recursos.recursos.entity.Especialidad;
import com.recursos.recursos.entity.Medico;
import com.recursos.recursos.services.EspecialidadServicio;


import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(EspecialidadControlador.class)
class EspecialidadControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EspecialidadServicio especialidadServicio;

    @Test
    public void testCrearEspecialidad() throws Exception {
        // Datos de prueba
        Especialidad especialidad = new Especialidad();
        especialidad.setEspecialidad_id(1);
        especialidad.setEspecialidad("Cardiología");
        List<Medico> medicos = new ArrayList<>();
        Medico med1 = new Medico(1);
        Medico med2 = new Medico(2);
        medicos.add(med1);
        medicos.add(med2);
        especialidad.setMedicoEspecialidades(medicos);

        // Simula el comportamiento del servicio de especialidad
        given(especialidadServicio.guardarEspecialidad(any(Especialidad.class))).willReturn(especialidad);

        // Realiza la solicitud POST con el cuerpo de la especialidad en formato JSON
        mockMvc.perform(post("/especialidad/crearEspecialidad")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"especialidad_id\":1,\"especialidad\":\"Cardiología\",\"medicoEspecialidades\":[]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.especialidad_id").value(1))
                .andExpect(jsonPath("$.especialidad").value("Cardiología"))
                .andExpect(jsonPath("$.medicoEspecialidades").isArray());
    }

    @Test
    public void testListarEspecialidades() throws Exception {
        // Datos de prueba
        Especialidad especialidad1 = new Especialidad(1, "Cardiología");
        Especialidad especialidad2 = new Especialidad(2, "Dermatología");
        List<Especialidad> especialidades = new ArrayList<>();
        especialidades.add(especialidad1);
        especialidades.add(especialidad2);

        // Simula el comportamiento del servicio de especialidad
        given(especialidadServicio.listarEspecialidades()).willReturn(especialidades);

        // Realiza la solicitud GET al endpoint /especialidad/obtenerEspecialidades
        mockMvc.perform(get("/especialidad/obtenerEspecialidades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].especialidad_id", is(1)))
                .andExpect(jsonPath("$[0].especialidad", is("Cardiología")))
                .andExpect(jsonPath("$[1].especialidad_id", is(2)))
                .andExpect(jsonPath("$[1].especialidad", is("Dermatología")));
    }


    @Test
    public void testEliminarEspecialidad() throws Exception {
        // Simula el comportamiento del servicio de especialidad
        doNothing().when(especialidadServicio).eliminarEspecialidadPorId(2);

        // Realiza la solicitud DELETE al endpoint /especialidad/eliminarEspecialidad/{id}
        mockMvc.perform(delete("/especialidad/eliminarEspecialidad/2"))
                .andExpect(status().isOk());
    }

}