package com.recursos.recursos.repositorio;

import com.recursos.recursos.entity.Cita;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CitaRepositorioTest {
    @Autowired
    private CitaRepositorio citaRepositorio;



    @Test
    void testGuardarCita() {
        Integer cita_id = 11;
        LocalDate fechaCita = LocalDate.of(2023, 6, 17); // Fecha específica: 17 de junio de 2023
        LocalTime hora = LocalTime.of(7, 30); // Hora específica: 09:30 AM
        boolean estado = false;

        Date fechaCitaDate = java.sql.Date.valueOf(fechaCita); // Convertir LocalDate a java.sql.Date


        Cita cita = new Cita(cita_id, fechaCitaDate, Time.valueOf(hora), estado);
        Cita citaGuardada = citaRepositorio.save(cita);

        Assertions.assertNotNull(citaGuardada);
    }




    @Test
    void testObtenerCitaPorId() {
        Integer cita_id = 11;
        LocalDate fechaCita = LocalDate.of(2023, 6, 17); // Fecha específica: 17 de junio de 2023
        LocalTime hora = LocalTime.of(9, 30); // Hora específica: 09:30 AM
        boolean estado = false;

        Date fechaCitaDate = java.sql.Date.valueOf(fechaCita); // Convertir LocalDate a java.sql.Date

        Cita cita = new Cita(cita_id, fechaCitaDate, Time.valueOf(hora), estado);
        citaRepositorio.save(cita);

        Optional<Cita> personaObtenidaOptional = citaRepositorio.findById(11);

        // Verificar que la persona obtenida no sea nula
        Assertions.assertNotNull(personaObtenidaOptional);

        // Verificar si el Optional contiene un valor antes de acceder a él
        if (personaObtenidaOptional.isPresent()) {
            Cita citaObtenida = personaObtenidaOptional.get();

            // Verificar que la información de la cita obtenida coincida con la información de la cita de prueba
            Assertions.assertEquals(cita.getCita_id(), citaObtenida.getCita_id());
            Assertions.assertEquals(cita.getFechaCita(), citaObtenida.getFechaCita());
            Assertions.assertEquals(cita.getHora(), citaObtenida.getHora());
            Assertions.assertEquals(cita.isEstado(), citaObtenida.isEstado());

        } else {
            // Si el Optional está vacío, lanzar una excepción o realizar alguna otra acción según lo requieras

        }


    }

    @Test
    public void testListarCita(){
        List<Cita> citas = citaRepositorio.findAll();
        assertThat(citas).size().isGreaterThan(0);
    }

    @Test
    public void eliminarCitas(){

        Integer id = 18;
        boolean existe =  citaRepositorio.findById(id).isPresent();
        citaRepositorio.deleteById(id);
        boolean Noexiste =  citaRepositorio.findById(id).isPresent();

        assertTrue(existe);
        assertFalse(Noexiste);

    }
}