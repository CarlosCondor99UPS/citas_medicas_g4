package ups.edu.ec.sistemacitasmedicas.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ups.edu.ec.sistemacitasmedicas.modelo.Especialidad;
import ups.edu.ec.sistemacitasmedicas.modelo.Medico;
import ups.edu.ec.sistemacitasmedicas.modelo.Persona;
import ups.edu.ec.sistemacitasmedicas.servicio.EspecialidadServicio;
import ups.edu.ec.sistemacitasmedicas.servicio.MedicioServicio;
import ups.edu.ec.sistemacitasmedicas.servicio.PersonaServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicoControlador")
@CrossOrigin("*")
public class MedicoControlador {

    @Autowired
    PersonaServicio personaServicio;

    @Autowired
    EspecialidadServicio especialidadServicio;

    @Autowired
    MedicioServicio medicioServicio;

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
        return medicioServicio.guardarMedicoEspecialidad(medico,medicoEspecialidad);

    }
    @GetMapping("/obtenerMedicos")
    public List<Medico> listarMedicos(){
        return medicioServicio.listarMedicos();
    }


    @GetMapping("/{medico_id}")
    public Medico obtenerMedicoId(@PathVariable("medico_id")Integer medico_id){
        return medicioServicio.obtenerMedicoPorId(medico_id);
    }

    @GetMapping("/obtenerMedicoIdPorPersonaId/{persona_id}")
    public ResponseEntity<Integer> obtenerMedicoIdPorPersonaId(@PathVariable("persona_id") Integer persona_id) {
        Medico medico = medicioServicio.obtenerMedicoIdPorPersonaId(persona_id);
        if (medico != null) {
            return ResponseEntity.ok().body(medico.getMedico_id());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
