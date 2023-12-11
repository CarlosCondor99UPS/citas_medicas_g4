package com.recursos.recursos.repositorio;

import com.recursos.recursos.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CitaRepositorio extends JpaRepository<Cita, Integer> {

    Optional<Cita> findById(Integer cita_id);


}