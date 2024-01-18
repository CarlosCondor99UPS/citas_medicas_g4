package ups.edu.ec.sistemacitasmedicas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import ups.edu.ec.sistemacitasmedicas.modelo.Especialidad;

import java.util.Optional;

public interface EspecialidadRepositorio extends JpaRepository<Especialidad, Integer> {

    Optional<Especialidad> findById(Integer especialidad_id);


}
