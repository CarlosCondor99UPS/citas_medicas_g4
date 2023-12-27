package com.recursos.recursos.repositorio;

import com.recursos.recursos.entity.DetalleFactura;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DetalleFacturaRepositorioTest {
    @Autowired
    private DetalleFacturaRepositorio detalleFacturaRepositorio;



    @Test
    void testGuardarFacturaDetalle(){

        DetalleFactura DetalleFactura = new DetalleFactura(11, "Producto 1", 2, 10.0, 20.0);

        DetalleFactura personaGuardado = detalleFacturaRepositorio.save(DetalleFactura);

        Assertions.assertNotNull(personaGuardado);
    }
    @Test
    void testObtenerEspecialidadPorId() {
        DetalleFactura detalleFactura = new DetalleFactura(11, "Producto 1", 2, 10.0, 20.0);
        detalleFacturaRepositorio.save(detalleFactura);

        Optional<DetalleFactura> personaObtenidaOptional = detalleFacturaRepositorio.findById(11);

        // Verificar que la persona obtenida no sea nula
        Assertions.assertNotNull(personaObtenidaOptional);

        // Verificar si el Optional contiene un valor antes de acceder a él
        if (personaObtenidaOptional.isPresent()) {
            DetalleFactura detalleFacturaObtenido = personaObtenidaOptional.get();

            // Verificar que la información de la persona obtenida coincida con la información de la persona de prueba
            Assertions.assertEquals(detalleFactura.getDetalleFactura_id(), detalleFacturaObtenido.getDetalleFactura_id());
            Assertions.assertEquals(detalleFactura.getNombre(), detalleFacturaObtenido.getNombre());
            Assertions.assertEquals(detalleFactura.getCantidad(), detalleFacturaObtenido.getCantidad());
            Assertions.assertEquals(detalleFactura.getPrecioUnitario(), detalleFacturaObtenido.getPrecioUnitario());
            Assertions.assertEquals(detalleFactura.getTotal(), detalleFacturaObtenido.getTotal());


        } else {
            // Si el Optional está vacío, lanzar una excepción o realizar alguna otra acción según lo requieras

        }


    }

    @Test
    public void testListarDetalles(){
        List<DetalleFactura> detalleFacturas = detalleFacturaRepositorio.findAll();
        assertThat(detalleFacturas).size().isGreaterThan(0);
    }

    @Test
    public void eliminarDetalles(){

        Integer id = 2;
        boolean existe =  detalleFacturaRepositorio.findById(id).isPresent();
        detalleFacturaRepositorio.deleteById(id);
        boolean Noexiste =  detalleFacturaRepositorio.findById(id).isPresent();

        assertTrue(existe);
        assertFalse(Noexiste);

    }

}