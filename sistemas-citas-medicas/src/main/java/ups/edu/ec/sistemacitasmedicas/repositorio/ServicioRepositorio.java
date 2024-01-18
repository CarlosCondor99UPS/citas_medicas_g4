package ups.edu.ec.sistemacitasmedicas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import ups.edu.ec.sistemacitasmedicas.modelo.Servicio;

import java.util.Optional;

public interface ServicioRepositorio extends JpaRepository<Servicio, Integer> {

    Optional<Servicio> findById(Integer servicio_id);
}
