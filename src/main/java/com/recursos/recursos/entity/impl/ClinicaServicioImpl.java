package com.recursos.recursos.entity.impl;

import com.recursos.recursos.entity.Clinica;
import com.recursos.recursos.repositorio.ClinicaRepositorio;
import com.recursos.recursos.services.ClinicaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicaServicioImpl implements ClinicaServicio {
    @Autowired
     ClinicaRepositorio clinicaRepositorio;

    //CREAR
    @Override
    public Clinica guardarprescripcion(Clinica clinica, List<Clinica> clinicaPersona) {
        return clinicaRepositorio.save(clinica);
    }

    //LISTAR
    @Override
    public List<Clinica> listarClinica() {
        return clinicaRepositorio.findAll();
    }

    //LISTAR POR ID
    @Override
    public Clinica obtenerClinicaPorId(Integer clinica_id) {
        Optional<Clinica> clinicaOptional = clinicaRepositorio.findById(clinica_id);
        return clinicaOptional.orElse(null);
    }


    @Override
    public Optional<Clinica> get(Integer clinica_id) {
        return clinicaRepositorio.findById(clinica_id);
    }

    //ELIMINAR
    @Override
    public void delete(Integer id) {
        clinicaRepositorio.deleteById(id);
    }


    //ACTUALIZAR
    public Clinica actualizarClinica(Integer id, Clinica clinicaActualizada) {
        Optional<Clinica> clinicaOptional = clinicaRepositorio.findById(id);
        if (clinicaOptional.isPresent()) {
            Clinica clinicaExistente = clinicaOptional.get();
            clinicaExistente.setCertificados(clinicaActualizada.getCertificados());
            clinicaExistente.setOrdenes(clinicaActualizada.getOrdenes());
            clinicaExistente.setPrescripciones(clinicaActualizada.getPrescripciones());
            return clinicaRepositorio.save(clinicaExistente);
        } else {
            return null;
        }
    }


}
