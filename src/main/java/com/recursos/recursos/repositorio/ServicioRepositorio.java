package com.recursos.recursos.repositorio;

import com.recursos.recursos.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicioRepositorio extends JpaRepository<Servicio, Integer> {

    Optional<Servicio> findById(Integer servicio_id);
}