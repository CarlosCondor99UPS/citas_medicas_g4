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

@Service
@Transactional
public class EspecialidadServicio {
    @PersistenceContext
    private EntityManager entityManager;

    private final RestTemplate restTemplate;

    public EspecialidadServicio(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    //LISTAR
    public List<Especialidad> getEspecialidad() {
        String jpql = "SELECT c FROM Especialidad c";
        Query query = entityManager.createQuery(jpql, Especialidad.class);
        List<Especialidad> lista = query.getResultList();
        return lista;
    }
    //CREAR
    public void insertarEspecialidad(Especialidad especialidad) {
        if (!existeEspecialidad(especialidad.getEspecialidad())) {
            entityManager.persist(especialidad);
            System.out.println("Especialidad Creada");

        } else {
            System.out.println("La especialidad ya existe");
        }
    }
    private boolean existeEspecialidad(String nombreEspecialidad) {
        Query query = entityManager.createQuery("SELECT COUNT(e) FROM Especialidad e WHERE e.especialidad = :especialidad")
                .setParameter("especialidad", nombreEspecialidad);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }
    //ACTUALIZAR
    public Especialidad actualizarEspecialidad(int especialidad_id, Especialidad especialidadActualizado) {
        Especialidad especialidadExistente = entityManager.find(Especialidad.class, especialidad_id);
        if (especialidadExistente != null) {
            especialidadExistente.setEspecialidad(especialidadActualizado.getEspecialidad());
            entityManager.merge(especialidadExistente);
        }
        else{
            System.out.println("No se actualizó la especialidad");
        }
        return especialidadExistente;
    }
    //ELIMINAR
    public void eliminarEspecialidad(int id) {
        Especialidad especialidadExistente = entityManager.find(Especialidad.class, id);
        if (especialidadExistente != null) {
            entityManager.remove(especialidadExistente);
        }
    }
    //LISTAR POR ID
    public Especialidad buscarPorId(int id) {
        return entityManager.find(Especialidad.class, id);
    }






}
