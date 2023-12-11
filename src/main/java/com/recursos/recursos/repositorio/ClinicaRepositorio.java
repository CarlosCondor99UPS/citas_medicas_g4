package com.recursos.recursos.repositorio;

import com.recursos.recursos.entity.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicaRepositorio extends JpaRepository<Clinica, Integer> {

    Optional<Clinica> findById(Integer clinica_id);
}
