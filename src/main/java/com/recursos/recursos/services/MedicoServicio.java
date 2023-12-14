package com.recursos.recursos.services;

import com.recursos.recursos.entity.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoServicio {

    List<Medico> listarMedicos();
    Medico obtenerMedicoPorId(Integer id);
    Optional<Medico> get(Integer id);
    Medico guardarMedicoEspecialidad(Medico medico, List<Medico> medicoEspecialidades) throws Exception;


}