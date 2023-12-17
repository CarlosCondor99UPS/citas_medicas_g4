package com.recursos.recursos.controller;

import com.recursos.recursos.entity.Clinica;
import com.recursos.recursos.entity.Medico;
import com.recursos.recursos.entity.Persona;

import com.recursos.recursos.services.ClinicaServicio;
import com.recursos.recursos.services.MedicoServicio;
import com.recursos.recursos.services.PersonaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/clinica")
@CrossOrigin("*")
public class ClinicaControlador {
    @Autowired
    ClinicaServicio clinicaServicio;
    @Autowired
    MedicoServicio medicoServicio;
    @Autowired
    PersonaServicio personaServicio;

    @PostMapping("/crear/{medico_id}/{persona_id}")
    public Clinica guardarClinica(@PathVariable("medico_id") Integer medico_id,
                                  @PathVariable("persona_id") Integer persona_id,
                                  @RequestBody Clinica clinica) throws Exception{

        List<Clinica> clinicaPersona = new ArrayList<>();
        Persona persona = new Persona();
        Optional<Persona> personaOptional = personaServicio.get(persona_id);
        Medico medico = new Medico();
        Optional<Medico> medicoOptional = medicoServicio.get(medico_id);

        persona.setPersona_id(personaOptional.get().getPersona_id());
        medico.setMedico_id(medicoOptional.get().getMedico_id());

        if (!personaOptional.isPresent()) {
            throw new Exception("La persona no existe");
        }

        if (!medicoOptional.isPresent()) {
            throw new Exception("El medico no existe");
        }

        clinica.setPersona(persona);
        clinica.setMedico(medico);

        clinicaPersona.add(clinica);

        return clinicaServicio.guardarprescripcion(clinica, clinicaPersona);

    }



}
