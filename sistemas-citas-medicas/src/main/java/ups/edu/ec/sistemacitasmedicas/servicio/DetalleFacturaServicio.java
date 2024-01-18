package ups.edu.ec.sistemacitasmedicas.servicio;

import ups.edu.ec.sistemacitasmedicas.modelo.DetalleFactura;

import java.util.List;
import java.util.Optional;

public interface DetalleFacturaServicio {

    DetalleFactura save(DetalleFactura detalleFactura);

    Optional<DetalleFactura>get(Integer id);

    List<DetalleFactura> findAll();


}
