package com.recursos.recursos.services;

import com.recursos.recursos.entity.DetalleFactura;

import java.util.List;
import java.util.Optional;

public interface DetalleFacturaServicio {

    DetalleFactura save(DetalleFactura detalleFactura);

    Optional<DetalleFactura> get(Integer id);

    List<DetalleFactura> findAll();


}