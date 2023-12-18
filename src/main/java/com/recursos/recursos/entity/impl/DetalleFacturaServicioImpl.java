package com.recursos.recursos.entity.impl;

import com.recursos.recursos.entity.DetalleFactura;
import com.recursos.recursos.repositorio.DetalleFacturaRepositorio;
import com.recursos.recursos.services.DetalleFacturaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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