package com.recursos.recursos.services;

import com.recursos.recursos.entity.Especialidad;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface EspecialidadServicio {

    Especialidad guardarEspecialidad(Especialidad especialidades);
    List<Especialidad> listarEspecialidades();
    Especialidad obtenerEspecialidadPorId(Integer id);
    //Optional<Especialidad> get(Integer id);
    void eliminarEspecialidadPorId(Integer id);
    Especialidad actualizarEspecialidad(Integer id, Especialidad especialidadActualizada);


}
