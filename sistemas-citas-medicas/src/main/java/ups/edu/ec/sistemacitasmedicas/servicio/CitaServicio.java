package ups.edu.ec.sistemacitasmedicas.servicio;

import ups.edu.ec.sistemacitasmedicas.modelo.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaServicio {

//    Cita guardarCita(Cita cita);
    List<Cita> listarCitas();
    Cita obtenerCitaPorId(Integer id);
    Optional<Cita> get(Integer id);
    Cita guardarCitaMedico(Cita cita, List<Cita> citaMedico) throws Exception;



}
