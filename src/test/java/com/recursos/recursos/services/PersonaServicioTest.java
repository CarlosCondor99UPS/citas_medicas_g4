package com.recursos.recursos.services;

import com.recursos.recursos.entity.Persona;
import com.recursos.recursos.entity.impl.PersonaServicioImpl;
import com.recursos.recursos.repositorio.PersonaRepositorio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PersonaServicioTest {
    @Mock
    private PersonaRepositorio personaRepositorio;

    @InjectMocks
    private PersonaServicioImpl personaServicio;

    private Persona persona;

    @Test
    public void testGuardarEmpleado(){

        Persona persona = new Persona("0151802089", "Ariana", "Guaillazaca", "Gualaceo", "0993235895", "noemi123@gmail.com","activo",
                "27-11-2001", "femenino", "Empleado");

//        given(personaRepositorio.findByCedula(persona.getCedula())).willReturn(persona);

        given(personaRepositorio.save(persona)).willReturn(persona);

        Persona personaGuardado = personaServicio.crearPersona(persona);

        assertThat(personaGuardado);

    }

}