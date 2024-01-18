package ups.edu.ec.sistemacitasmedicas.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ups.edu.ec.sistemacitasmedicas.modelo.DetalleFactura;
import ups.edu.ec.sistemacitasmedicas.repositorio.DetalleFacturaRepositorio;
import ups.edu.ec.sistemacitasmedicas.servicio.DetalleFacturaServicio;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaServicioImpl implements DetalleFacturaServicio {

    @Autowired
    DetalleFacturaRepositorio detalleFacturaRepositorio;

    @Override
    public DetalleFactura save(DetalleFactura detalleFactura) {
        return detalleFacturaRepositorio.save(detalleFactura);
    }

    @Override
    public Optional<DetalleFactura> get(Integer id) {
        return detalleFacturaRepositorio.findById(id);
    }

    @Override
    public List<DetalleFactura> findAll() {
        return detalleFacturaRepositorio.findAll();
    }



}
