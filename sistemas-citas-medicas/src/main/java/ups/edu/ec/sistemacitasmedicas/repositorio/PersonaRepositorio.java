package ups.edu.ec.sistemacitasmedicas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ups.edu.ec.sistemacitasmedicas.modelo.Medico;
import ups.edu.ec.sistemacitasmedicas.modelo.Persona;

import java.util.List;
import java.util.Optional;


public interface PersonaRepositorio extends JpaRepository<Persona,Integer> {
    Persona findByCedula(String cedula);
    Optional<Persona> findById(Integer persona_id);


}
