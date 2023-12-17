package com.recursos.recursos.services;

import com.recursos.recursos.entity.Clinica;


import java.util.List;
import java.util.Optional;

public interface ClinicaServicio {
    List<Clinica> listarClinica();
    Optional<Clinica> get(Integer id);
    Clinica guardarprescripcion(Clinica clinica, List<Clinica> clinicaPersona) throws Exception;
    Clinica obtenerClinicaPorId(Integer clinica_id);

    void delete(Integer id);





}
