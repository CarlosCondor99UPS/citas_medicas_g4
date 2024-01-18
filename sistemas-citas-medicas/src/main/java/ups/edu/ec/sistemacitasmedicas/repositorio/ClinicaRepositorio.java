package ups.edu.ec.sistemacitasmedicas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import ups.edu.ec.sistemacitasmedicas.modelo.Clinica;

import java.util.Optional;

public interface ClinicaRepositorio extends JpaRepository<Clinica, Integer> {

    Optional<Clinica> findById(Integer clinica_id);
}
