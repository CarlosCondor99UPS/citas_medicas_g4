package com.recursos.recursos.services;

import com.recursos.recursos.entity.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaServicio {

    Persona obtenerPersonaPorId(Integer persona_id);

    Persona crearPersona(Persona persona);

    void eliminarPersona(Integer id);

    Optional<Persona> get(Integer id);

    List<Persona> listarPersonas();



}
