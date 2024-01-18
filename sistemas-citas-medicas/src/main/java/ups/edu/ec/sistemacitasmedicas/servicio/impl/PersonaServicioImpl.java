package ups.edu.ec.sistemacitasmedicas.servicio.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ups.edu.ec.sistemacitasmedicas.Exceptions.PersonaExistenteException;
import ups.edu.ec.sistemacitasmedicas.Exceptions.PersonaNoEncontradaException;
import ups.edu.ec.sistemacitasmedicas.modelo.Especialidad;
import ups.edu.ec.sistemacitasmedicas.modelo.Medico;
import ups.edu.ec.sistemacitasmedicas.modelo.Persona;
import ups.edu.ec.sistemacitasmedicas.repositorio.PersonaRepositorio;
import ups.edu.ec.sistemacitasmedicas.servicio.PersonaServicio;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServicioImpl implements PersonaServicio {

    @Autowired
    private PersonaRepositorio personaRepositorio;

    public Persona obtenerPersonaPorId(Integer persona_id) {
        Optional<Persona> personaOptional = personaRepositorio.findById(persona_id);
        return personaOptional.orElse(null);
    }

//    public Persona crearPersona(Persona persona) {
//        // Verificar si ya existe una persona con la misma cédula
//        Persona personaExistente = personaRepositorio.findByCedula(persona.getCedula());
//        if (personaExistente.equals(persona.getCedula())) {
//            throw new PersonaExistenteException("Ya existe una persona con la misma cédula.");
//        }
//        // Asignar roles por tipo
//        persona.asignarRolesPorTipo();
//
//        return personaRepositorio.save(persona);
//    }

    public Persona crearPersona(Persona persona) {
        // Verificar si ya existe una persona con la misma cédula
        Persona personaExistente = personaRepositorio.findByCedula(persona.getCedula());
        if (personaExistente != null && personaExistente.equals(persona.getCedula())) {
            throw new PersonaExistenteException("Ya existe una persona con la misma cédula.");
        }

        // Asignar roles por tipo
        persona.asignarRolesPorTipo();

        return personaRepositorio.save(persona);
    }

    public void eliminarPersona(Integer id) {
        Optional<Persona> personaOptional = personaRepositorio.findById(id);
        personaOptional.ifPresent(personaRepositorio::delete);
    }

    @Override
    public Optional<Persona> get(Integer persona_id) {
        return personaRepositorio.findById(persona_id);
    }

    @Override
    public List<Persona> listarPersonas() {
        return personaRepositorio.findAll();
    }


    public Persona actualizarPersona(Persona persona) {
        // Verificar si la persona existe en la base de datos
        Optional<Persona> personaExistente = personaRepositorio.findById(persona.getPersona_id());
        if (!personaExistente.isPresent()) {
            throw new PersonaNoEncontradaException("No se encontró la persona a actualizar.");
        }

        // Actualizar los datos de la persona existente con los nuevos datos
        Persona personaActualizada = personaExistente.get();
        personaActualizada.setCedula(persona.getCedula());
        personaActualizada.setNombre(persona.getNombre());
        personaActualizada.setApellido(persona.getApellido());
        personaActualizada.setDireccion(persona.getDireccion());
        personaActualizada.setTelefono(persona.getTelefono());
        personaActualizada.setCorreo(persona.getCorreo());
        personaActualizada.setEstado(persona.getEstado());
        personaActualizada.setFechaNacimiento(persona.getFechaNacimiento());
        personaActualizada.setSexo(persona.getSexo());
        personaActualizada.setTipo(persona.getTipo());
        personaActualizada.setEsCliente(persona.isEsCliente());
        personaActualizada.setEsMedico(persona.isEsMedico());
        personaActualizada.setEsEmpleado(persona.isEsEmpleado());
        // Actualiza otros atributos según tu modelo de datos

        // Guardar los cambios en la base de datos
        return personaRepositorio.save(personaActualizada);
    }




}


