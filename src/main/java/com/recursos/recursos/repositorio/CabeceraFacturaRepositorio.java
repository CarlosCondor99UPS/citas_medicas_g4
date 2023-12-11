package com.recursos.recursos.repositorio;

import com.recursos.recursos.entity.CabeceraFactura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CabeceraFacturaRepositorio extends JpaRepository<CabeceraFactura, Integer> {

    //    List<CabeceraFactura> findByPersona(Persona persona);
    Optional<CabeceraFactura> findById(Integer cabeceraFactura_id);

}
