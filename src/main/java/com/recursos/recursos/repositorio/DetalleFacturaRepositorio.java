package com.recursos.recursos.repositorio;

import com.recursos.recursos.entity.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetalleFacturaRepositorio extends JpaRepository<DetalleFactura, Integer> {

    Optional<DetalleFactura> findById(Integer detalleFactura_id);

}
