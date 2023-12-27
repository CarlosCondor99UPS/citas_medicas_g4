package com.recursos.recursos.repositorio;

import java.util.List;
import java.util.Optional;

import com.recursos.recursos.entity.Persona;

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
class PersonaRepositorioTest {
    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Test
    void testGuardarPersona(){

        Persona persona1 = new Persona("0100324534", "santiago", "cabrera", "Gualaceo", "0998087966", "ssc@gmail.com","activo",
                "17-02-2002", "masculino", "Empleado");

        Persona personaGuardado = personaRepositorio.save(persona1);

        Assertions.assertNotNull(personaGuardado);

    }

    @Test
    void testObtenerPersonaPorId() {
        Persona persona = new Persona(10, "0100324534", "santiago", "cabrera", "Gualaceo", "0998087966",
                "ssc@gmail.com", "activo", "17-02-2002", "masculino", "Empleado");
        personaRepositorio.save(persona);

        Optional<Persona> personaObtenidaOptional = personaRepositorio.findById(10);

        // Verificar que la persona obtenida no sea nula
        Assertions.assertNotNull(personaObtenidaOptional);

        // Verificar si el Optional contiene un valor antes de acceder a él
        if (personaObtenidaOptional.isPresent()) {
            Persona personaObtenida = personaObtenidaOptional.get();

            // Verificar que la información de la persona obtenida coincida con la información de la persona de prueba
            Assertions.assertEquals(persona.getCedula(), personaObtenida.getCedula());
            Assertions.assertEquals(persona.getNombre(), personaObtenida.getNombre());
            Assertions.assertEquals(persona.getApellido(), personaObtenida.getApellido());
            Assertions.assertEquals(persona.getDireccion(), personaObtenida.getDireccion());
            Assertions.assertEquals(persona.getTelefono(), personaObtenida.getTelefono());
            Assertions.assertEquals(persona.getCorreo(), personaObtenida.getCorreo());
            Assertions.assertEquals(persona.getEstado(), personaObtenida.getEstado());
            Assertions.assertEquals(persona.getFechaNacimiento(), personaObtenida.getFechaNacimiento());
            Assertions.assertEquals(persona.getSexo(), personaObtenida.getSexo());
            Assertions.assertEquals(persona.getTipo(), personaObtenida.getTipo());
        } else {
            // Si el Optional está vacío, lanzar una excepción o realizar alguna otra acción según lo requieras
        }
    }

    @Test
    public void testListarPersonas(){
        List<Persona> personas = personaRepositorio.findAll();
        assertThat(personas).size().isGreaterThan(0);
    }

    @Test
    public void eliminarPersonas(){

        Integer id = 10;
        boolean existe =  personaRepositorio.findById(id).isPresent();
        personaRepositorio.deleteById(id);
        boolean Noexiste =  personaRepositorio.findById(id).isPresent();

        assertTrue(existe);
        assertFalse(Noexiste);

    }

}