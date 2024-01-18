package ups.edu.ec.sistemacitasmedicas.servicio;

import ups.edu.ec.sistemacitasmedicas.modelo.Clinica;

import java.util.List;
import java.util.Optional;

public interface ClinicaServicio {

    List<Clinica> listarClinica();
    Optional<Clinica> get(Integer id);
    Clinica guardarprescripcion(Clinica clinica, List<Clinica> clinicaPersona) throws Exception;
    Clinica obtenerClinicaPorId(Integer clinica_id);


}
