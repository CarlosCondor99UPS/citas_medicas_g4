package com.recursos.recursos.repositorio;


import java.util.List;
import java.util.Optional;

import com.recursos.recursos.entity.Especialidad;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EspecialidadRepositorioTest {
    @Autowired
    private EspecialidadRepositorio especialidadRepositorio;



    @Test
    void testGuardarEspecialidad(){

        Especialidad especialidad = new Especialidad(10,"Ginecologia");

        Especialidad personaGuardado = especialidadRepositorio.save(especialidad);

        Assertions.assertNotNull(personaGuardado);
    }
    @Test
    void testObtenerEspecialidadPorId() {
        Especialidad especialidad = new Especialidad(10,"Ginecologia");
        especialidadRepositorio.save(especialidad);

        Optional<Especialidad> personaObtenidaOptional = especialidadRepositorio.findById(10);

        // Verificar que la persona obtenida no sea nula
        Assertions.assertNotNull(personaObtenidaOptional);

        // Verificar si el Optional contiene un valor antes de acceder a él
        if (personaObtenidaOptional.isPresent()) {
            Especialidad personaObtenida = personaObtenidaOptional.get();

            // Verificar que la información de la persona obtenida coincida con la información de la persona de prueba
            Assertions.assertEquals(especialidad.getEspecialidad(), personaObtenida.getEspecialidad());

        } else {
            // Si el Optional está vacío, lanzar una excepción o realizar alguna otra acción según lo requieras

        }


    }

    @Test
    public void testListarEspecialiades(){
        List<Especialidad> especialidads = especialidadRepositorio.findAll();
        assertThat(especialidads).size().isGreaterThan(0);
    }

    @Test
    public void eliminarEspecialidades(){

        Integer id = 2;
        boolean existe =  especialidadRepositorio.findById(id).isPresent();
        especialidadRepositorio.deleteById(id);
        boolean Noexiste =  especialidadRepositorio.findById(id).isPresent();

        assertTrue(existe);
        assertFalse(Noexiste);

    }

}