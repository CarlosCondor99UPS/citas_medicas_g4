package com.recursos.recursos.repositorio;

import com.recursos.recursos.entity.Clinica;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClinicaRepositorioTest {
    @Autowired
    private ClinicaRepositorio clinicaRepositorio;



    @Test
    void testGuardarClinica(){

        Clinica clinica = new Clinica(11, "Prescripción 1", "Orden 1","Certificado 1");

        Clinica personaGuardado = clinicaRepositorio.save(clinica);

        Assertions.assertNotNull(personaGuardado);
    }
    @Test
    void testObtenerClinicaPorId() {
        Clinica clinica = new Clinica(11, "Prescripción 1", "Orden 1","Certificado 1");
        clinicaRepositorio.save(clinica);

        Optional<Clinica> personaObtenidaOptional = clinicaRepositorio.findById(11);

        // Verificar que la persona obtenida no sea nula
        Assertions.assertNotNull(personaObtenidaOptional);

        // Verificar si el Optional contiene un valor antes de acceder a él
        if (personaObtenidaOptional.isPresent()) {
            Clinica clinicaObtenida = personaObtenidaOptional.get();

            // Verificar que la información de la clinica obtenida coincida con la información de la clinica de prueba
            Assertions.assertEquals(clinica.getPrescripciones(), clinicaObtenida.getPrescripciones());
            Assertions.assertEquals(clinica.getOrdenes(), clinicaObtenida.getOrdenes());
            Assertions.assertEquals(clinica.getCertificados(), clinicaObtenida.getCertificados());



        } else {
            // Si el Optional está vacío, lanzar una excepción o realizar alguna otra acción según lo requieras

        }


    }

    @Test
    public void testListarClinica(){
        List<Clinica> clinicas = clinicaRepositorio.findAll();
        assertThat(clinicas).size().isGreaterThan(0);
    }

    @Test
    public void eliminarClinicas(){

        Integer id = 2;
        boolean existe =  clinicaRepositorio.findById(id).isPresent();
        clinicaRepositorio.deleteById(id);
        boolean Noexiste =  clinicaRepositorio.findById(id).isPresent();

        assertTrue(existe);
        assertFalse(Noexiste);

    }

}