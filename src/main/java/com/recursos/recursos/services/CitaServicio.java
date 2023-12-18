package com.recursos.recursos.services;

import com.recursos.recursos.entity.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaServicio {

    List<Cita> listarCitas();
    Cita obtenerCitaPorId(Integer id);
    Optional<Cita> get(Integer id);
    Cita guardarCitaMedico(Cita cita, List<Cita> citaMedico) throws Exception;



}