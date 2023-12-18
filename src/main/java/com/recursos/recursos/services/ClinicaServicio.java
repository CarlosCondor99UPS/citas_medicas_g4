package com.recursos.recursos.services;

import com.recursos.recursos.entity.Clinica;

import java.util.List;
import java.util.Optional;

public interface ClinicaServicio {

    //LISTAR
    List<Clinica> listarClinica();
    Optional<Clinica> get(Integer id);

    //CREAR
    Clinica guardarprescripcion(Clinica clinica, List<Clinica> clinicaPersona) throws Exception;

    //LISTAR POR ID
    Clinica obtenerClinicaPorId(Integer clinica_id);

    //ELIMINAR
    void delete(Integer id);

    //ACTUALIZAR
    Clinica actualizarClinica(Integer id, Clinica clinicaActualizada);





}
