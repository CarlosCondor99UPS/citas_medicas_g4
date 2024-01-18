package ups.edu.ec.sistemacitasmedicas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import ups.edu.ec.sistemacitasmedicas.modelo.CabeceraFactura;

import java.util.Optional;

public interface CabeceraFacturaRepositorio extends JpaRepository<CabeceraFactura, Integer> {

//    List<CabeceraFactura> findByPersona(Persona persona);
    Optional<CabeceraFactura> findById(Integer cabeceraFactura_id);

}
