package com.recursos.recursos.repositorio;

import java.util.List;
import java.util.Optional;

import com.recursos.recursos.entity.Medico;

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
class MedicoRepositorioTest {
    @Autowired
    private MedicoRepositorio medicoRepositorio;



    @Test
    void testGuardarMedico(){

        Medico medico = new Medico(10,"0124578548");

        Medico personaGuardado = medicoRepositorio.save(medico);

        Assertions.assertNotNull(personaGuardado);
    }
    @Test
    void testObtenerMedicoPorId() {
        Medico medico = new Medico(10,"0124578548");
        medicoRepositorio.save(medico);

        Optional<Medico> personaObtenidaOptional = medicoRepositorio.findById(10);

        // Verificar que la persona obtenida no sea nula
        Assertions.assertNotNull(personaObtenidaOptional);

        // Verificar si el Optional contiene un valor antes de acceder a él
        if (personaObtenidaOptional.isPresent()) {
            Medico personaObtenida = personaObtenidaOptional.get();

            // Verificar que la información de la persona obtenida coincida con la información de la persona de prueba
            Assertions.assertEquals(medico.getTelenoConsultorio(), personaObtenida.getTelenoConsultorio());

        } else {
            // Si el Optional está vacío, lanzar una excepción o realizar alguna otra acción según lo requieras

        }


    }

    @Test
    public void testListarMedicos(){
        List<Medico> medicos = medicoRepositorio.findAll();
        assertThat(medicos).size().isGreaterThan(0);
    }

    @Test
    public void eliminarMedicos(){

        Integer id = 2;
        boolean existe =  medicoRepositorio.findById(id).isPresent();
        medicoRepositorio.deleteById(id);
        boolean Noexiste =  medicoRepositorio.findById(id).isPresent();

        assertTrue(existe);
        assertFalse(Noexiste);

    }

}