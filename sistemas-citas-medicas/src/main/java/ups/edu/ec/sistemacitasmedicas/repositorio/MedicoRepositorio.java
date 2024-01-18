package ups.edu.ec.sistemacitasmedicas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ups.edu.ec.sistemacitasmedicas.modelo.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoRepositorio extends JpaRepository<Medico,Integer> {
    @Query("SELECT m FROM Medico m WHERE m.persona.persona_id = :personaId")
    Optional<Medico> findByPersonaId(@Param("personaId") Integer personaId);

}
