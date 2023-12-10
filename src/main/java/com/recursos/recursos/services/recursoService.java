package com.recursos.recursos.services;

import com.recursos.recursos.entity.registro;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;


@Service
@Transactional
public class recursoService {
    @PersistenceContext
    private EntityManager entityManager;
    private final RestTemplate restTemplate;

    public recursoService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<registro> getListaTodo() {
        String jpql = "SELECT c FROM registro c";
        Query query = entityManager.createQuery(jpql, registro.class);
        List<registro> lista = query.getResultList();
        return lista;
    }

    public void insertarRegistro(registro registro) {
        entityManager.persist(registro);
        int catalogoId = registro.getCatalogoId();
        //catalogo catalogo = restTemplate.getForObject("http://catalogo-ms/catalogos/{id}", Catalogo.class, catalogoId);
        // Asociar el catálogo obtenido al registro
       /* registro.setCatalogo(catalogo);*/

    }

    public registro actualizarRegistro(int id, registro registroActualizado) {
        registro registroExistente = entityManager.find(registro.class, id);
        if (registroExistente != null) {
            registroExistente.setDetalle(registroExistente.getDetalle());

            entityManager.merge(registroExistente);
        }
        return registroExistente;
    }

    public void eliminarRegistro(int id) {
        registro registroExistente = entityManager.find(registro.class, id);
        if (registroExistente != null) {
            entityManager.remove(registroExistente);
        }
    }

    public registro buscarPorId(int id) {
        return entityManager.find(registro.class, id);
    }
}
