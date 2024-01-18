package ups.edu.ec.sistemacitasmedicas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import ups.edu.ec.sistemacitasmedicas.modelo.DetalleFactura;

import java.util.Optional;

public interface DetalleFacturaRepositorio extends JpaRepository<DetalleFactura, Integer> {

    Optional<DetalleFactura> findById(Integer detalleFactura_id);

}
