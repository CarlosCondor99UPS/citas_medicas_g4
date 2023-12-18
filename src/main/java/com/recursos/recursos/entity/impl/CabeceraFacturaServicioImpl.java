package com.recursos.recursos.entity.impl;


import com.recursos.recursos.entity.CabeceraFactura;
import com.recursos.recursos.repositorio.CabeceraFacturaRepositorio;
import com.recursos.recursos.services.CabeceraFacturaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabeceraFacturaServicioImpl implements CabeceraFacturaServicio {

    @Autowired
    private CabeceraFacturaRepositorio cabeceraFacturaRepositorio;

    @Override
    public List<CabeceraFactura> findAll() {
        return cabeceraFacturaRepositorio.findAll();
    }

    @Override
    public Optional<CabeceraFactura> findById(Integer id) {
        return cabeceraFacturaRepositorio.findById(id);
    }

    @Override
    public CabeceraFactura save(CabeceraFactura cabeceraFactura) {
        return cabeceraFacturaRepositorio.save(cabeceraFactura);
    }

    @Override
    public CabeceraFactura obtenerCabeceraFacturaPorId(Integer cabeceraFactura_id) {
        Optional<CabeceraFactura> cabeceraFacturaOptional = cabeceraFacturaRepositorio.findById(cabeceraFactura_id);
        return cabeceraFacturaOptional.orElse(null);

    }

    @Override
    public Optional<CabeceraFactura> get(Integer id) {
        return cabeceraFacturaRepositorio.findById(id);
    }
}