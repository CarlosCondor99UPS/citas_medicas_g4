package ups.edu.ec.sistemacitasmedicas.servicio;

import ups.edu.ec.sistemacitasmedicas.modelo.CabeceraFactura;

import java.util.List;
import java.util.Optional;

public interface CabeceraFacturaServicio {

    List<CabeceraFactura> findAll();
    Optional<CabeceraFactura> findById(Integer id);
    CabeceraFactura save(CabeceraFactura cabeceraFactura);
//    List<CabeceraFactura> findByPersona(Persona persona);
    CabeceraFactura obtenerCabeceraFacturaPorId(Integer cabeceraFactura_id);
    Optional<CabeceraFactura> get(Integer id);

}
