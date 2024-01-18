package ups.edu.ec.sistemacitasmedicas.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ups.edu.ec.sistemacitasmedicas.modelo.Especialidad;
import ups.edu.ec.sistemacitasmedicas.modelo.Medico;
import ups.edu.ec.sistemacitasmedicas.modelo.Persona;
import ups.edu.ec.sistemacitasmedicas.repositorio.EspecialidadRepositorio;
import ups.edu.ec.sistemacitasmedicas.repositorio.MedicoRepositorio;
import ups.edu.ec.sistemacitasmedicas.servicio.EspecialidadServicio;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadServicioImpl implements EspecialidadServicio {

    @Autowired
    EspecialidadRepositorio especialidadRepositorio;

    @Autowired
    MedicoRepositorio medicoRepositorio;

    @Override
    public Especialidad guardarEspecialidad(Especialidad especialidades) {
        return especialidadRepositorio.save(especialidades);
    }

    @Override
    public List<Especialidad> listarEspecialidades() {
        return especialidadRepositorio.findAll();
    }

    @Override
    public Especialidad obtenerEspecialidadPorId(Integer id) {
        Optional<Especialidad> especialidadOptional = especialidadRepositorio.findById(id);
        return especialidadOptional.orElse(null);
    }

    @Override
    public Optional<Especialidad> get(Integer id) {
        return especialidadRepositorio.findById(id);
    }

    @Override
    public void eliminarEspecialidadPorId(Integer id) {
        // Obtener la especialidad por su ID
        Especialidad especialidad = especialidadRepositorio.findById(id).orElse(null);

        if (especialidad != null) {
            // Obtener la lista de médicos asociados a la especialidad
            List<Medico> medicos = especialidad.getMedicoEspecialidades();

            // Establecer la especialidad como nula en los médicos asociados
            for (Medico medico : medicos) {
                medico.setEspecialidad(null);
            }

            // Guardar los cambios en los médicos antes de eliminar la especialidad
            medicoRepositorio.saveAll(medicos);

            // Eliminar la especialidad
            especialidadRepositorio.deleteById(id);
        }
    }

    @Override
    public Especialidad actualizarEspecialidad(Especialidad especialidad) {
        return especialidadRepositorio.save(especialidad);
    }
}
