package ups.edu.ec.sistemacitasmedicas.servicio;

import ups.edu.ec.sistemacitasmedicas.modelo.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {

    Servicio save(Servicio servicio);
    Optional<Servicio> get(Integer id) throws Exception;
    Servicio update(Servicio servicio);
    void delete(Integer id);
    List<Servicio> findAll();
    Servicio obtenerServicioPorServicioId(Integer servicio_id);

}
