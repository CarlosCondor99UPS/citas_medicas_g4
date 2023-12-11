package com.recursos.recursos.entity.impl;

import com.recursos.recursos.entity.Especialidad;
import com.recursos.recursos.entity.Medico;
import com.recursos.recursos.repositorio.EspecialidadRepositorio;
import com.recursos.recursos.repositorio.MedicoRepositorio;
import com.recursos.recursos.services.EspecialidadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadServicioImpl implements EspecialidadServicio {
    @Autowired
    EspecialidadRepositorio especialidadRepositorio;

    @Autowired
    MedicoRepositorio medicoRepositorio;

    //CREAR
    @Override
    public Especialidad guardarEspecialidad(Especialidad especialidades) {
        return especialidadRepositorio.save(especialidades);
    }
    //LISTAR
    @Override
    public List<Especialidad> listarEspecialidades() {
        return especialidadRepositorio.findAll();
    }

    //LISTAR POR ID
    @Override
    public Especialidad obtenerEspecialidadPorId(Integer id) {
        Optional<Especialidad> especialidadOptional = especialidadRepositorio.findById(id);
        return especialidadOptional.orElse(null);
    }

    @Override
    public Optional<Especialidad> get(Integer id) {
        return especialidadRepositorio.findById(id);
    }

    //ELIMINAR
    @Override
    public void eliminarEspecialidadPorId(Integer id) {
        Especialidad especialidad = especialidadRepositorio.findById(id).orElse(null);
        if (especialidad != null) {
            List<Medico> medicos = especialidad.getMedicoEspecialidades();
            for (Medico medico : medicos) {
                medico.setEspecialidad(null);
            }
            medicoRepositorio.saveAll(medicos);
            especialidadRepositorio.deleteById(id);
        }
    }

    //ACTUALIZAR
    @Override
    public Especialidad actualizarEspecialidad(Integer id, Especialidad especialidadActualizada) {
        Optional<Especialidad> optionalEspecialidad = especialidadRepositorio.findById(id);
        if (optionalEspecialidad.isPresent()) {
            Especialidad especialidadExistente = optionalEspecialidad.get();
            especialidadExistente.setEspecialidad(especialidadActualizada.getEspecialidad());
            return especialidadRepositorio.save(especialidadExistente);
        } else {
            return null;
        }
    }

}
