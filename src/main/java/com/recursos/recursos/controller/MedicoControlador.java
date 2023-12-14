package com.recursos.recursos.controller;

import com.recursos.recursos.entity.Especialidad;
import com.recursos.recursos.entity.Medico;
import com.recursos.recursos.entity.Persona;
import com.recursos.recursos.services.EspecialidadServicio;
import com.recursos.recursos.services.MedicoServicio;
import com.recursos.recursos.services.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medico")
@CrossOrigin("*")
public class MedicoControlador {
    @Autowired
    PersonaServicio personaServicio;

    @Autowired
    EspecialidadServicio especialidadServicio;

    @Autowired
    MedicoServicio medicoServicio;
    @PostMapping("/guardarMedicoEspecialidad/{persona_id}/{especialidad_id}")
    public Medico guardarMedicoEspecialidad(@PathVariable("persona_id") Integer persona_id,
                                            @PathVariable("especialidad_id") Integer especialidad_id,
                                            @RequestBody Medico medico) throws Exception {


        List<Medico> medicoEspecialidad = new ArrayList<>();
        Especialidad especialidad = new Especialidad();
        Optional<Especialidad> especialidadOptional = especialidadServicio.get(especialidad_id);
        Persona persona = new Persona();
        Optional<Persona> personaOptional = personaServicio.get(persona_id);

        persona.setPersona_id(personaOptional.get().getPersona_id());
        especialidad.setEspecialidad_id(especialidadOptional.get().getEspecialidad_id());

        if (!personaOptional.isPresent()) {
            throw new Exception("La persona no existe");
        }

        if (!especialidadOptional.isPresent()) {
            throw new Exception("La especialidad no existe");
        }

        medico.setPersona(persona);
        medico.setEspecialidad(especialidad);

        medicoEspecialidad.add(medico);

        return medicoServicio.guardarMedicoEspecialidad(medico,medicoEspecialidad);

    }


}
