package ups.edu.ec.sistemacitasmedicas.servicio;

import ups.edu.ec.sistemacitasmedicas.modelo.Especialidad;

import java.util.List;
import java.util.Optional;

public interface EspecialidadServicio {

    Especialidad guardarEspecialidad(Especialidad especialidades);
    List<Especialidad> listarEspecialidades();
    Especialidad obtenerEspecialidadPorId(Integer id);
    Optional<Especialidad> get(Integer id);
    void eliminarEspecialidadPorId(Integer id);
    Especialidad actualizarEspecialidad(Especialidad especialidad);


}
