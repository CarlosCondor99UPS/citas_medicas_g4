package com.recursos.recursos.repositorio;

import com.recursos.recursos.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepositorio extends JpaRepository<Persona,Integer> {
    Persona findByCedula(String cedula);
    Optional<Persona> findById(Integer persona_id);

}
