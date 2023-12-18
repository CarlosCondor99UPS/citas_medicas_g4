package com.recursos.recursos.controller;

import com.recursos.recursos.entity.CabeceraFactura;
import com.recursos.recursos.entity.DetalleFactura;
import com.recursos.recursos.entity.Persona;
import com.recursos.recursos.entity.Servicio;
import com.recursos.recursos.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facturaControlador")
@CrossOrigin("*")
public class FacturaControlador {

    @Autowired
    private CabeceraFacturaServicio cabeceraFacturaServicio;

    @Autowired
    private DetalleFacturaServicio detalleFacturaServicio;

    @Autowired
    private PersonaServicio personaServicio;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private EmailSenderServicio emailSenderServicio;


    @PostMapping("/crear/{persona_id}/{servicio_id}/{cantidad}")
    public ResponseEntity<?> crearFactura(@PathVariable Integer persona_id,
                                          @PathVariable Integer servicio_id,
                                          @PathVariable int cantidad) {

        try {

            // Verificar si la persona y el servicio existen en la base de datos
            Persona personaOptional = personaServicio.obtenerPersonaPorId(persona_id);
            Optional<Servicio> servicioOptional = servicioService.get(servicio_id);

            if (personaOptional == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("La persona con el ID " + persona_id + " no existe.");
            }

            if (!servicioOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El servicio con el ID " + servicio_id + " no existe.");
            }

            // Crear la cabecera de la factura
            CabeceraFactura cabeceraFactura = new CabeceraFactura();
            cabeceraFactura.setFecha(new Date());
            cabeceraFactura.setEstado(true);

            cabeceraFactura.setPersona(personaOptional);

            // Crear el detalle de la factura
            DetalleFactura detalleFactura = new DetalleFactura();
            detalleFactura.setCantidad(cantidad);
            detalleFactura.setNombre(servicioOptional.get().getNombreServicio());
            detalleFactura.setPrecioUnitario(servicioOptional.get().getPrecio());
            detalleFactura.setTotal(cantidad * servicioOptional.get().getPrecio());
            detalleFactura.setServicio(servicioOptional.get());
            detalleFactura.setCabeceraFactura(cabeceraFactura);

            // Calcular los totales de la factura
            double subtotal = detalleFactura.getTotal();
            double totalIva = subtotal * servicioOptional.get().getIva();
            double totalFactura = subtotal + totalIva;

            cabeceraFactura.setSubtotal(subtotal);
            cabeceraFactura.setTotalIva(totalIva);
            cabeceraFactura.setTotalFactura(totalFactura);


            Servicio servicio = servicioOptional.get();

            if (servicio.getCantidad() == 0){
                throw new Exception("No esta ese servicio disponible");
            }

            int cantidadServicio = servicioOptional.get().getCantidad() - cantidad;
            servicio.setCantidad(cantidadServicio);


            servicioService.update(servicio);

            // Guardar la factura en la base de datos
            cabeceraFacturaServicio.save(cabeceraFactura);
            detalleFacturaServicio.save(detalleFactura);

            // Enviar el email con la factura
            String toUser = personaOptional.getCorreo(); // Obtén el correo electrónico del destinatario
            String subject = "Factura creada"; // Asunto del email

            StringBuffer content = new StringBuffer();
            content.append("Factura sistema citas medicas");

            content.append("\n");
            content.append("\n");

            content.append("Datos de la persona:");
            content.append("\n");
            content.append("Nombre: " + personaOptional.getNombre());
            content.append("\n");
            content.append("Apellido: " + personaOptional.getApellido());
            content.append("\n");
            content.append("Email: " + personaOptional.getCorreo());

            content.append("\n");
            content.append("\n");
            content.append("Detalles de la factura:");
            content.append("\n");
            content.append("Nombre: " + servicioOptional.get().getNombreServicio());
            content.append("\n");
            content.append("Cantidad: "+ cantidad);
            content.append("\n");
            content.append("Subtotal: " + subtotal);
            content.append("\n");
            content.append("Total IVA: " + totalIva);
            content.append("\n");
            content.append("Total factura: " + totalFactura);
            content.append("\n");

            emailSenderServicio.sendEmail(toUser, subject, content);

            return ResponseEntity.ok("La factura se ha creado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al crear la factura: " + e.getMessage());
        }
    }



    @GetMapping("/obtenerFacturas")
    public List<CabeceraFactura> obtenerFacturas() {
        return cabeceraFacturaServicio.findAll();
    }

    @GetMapping("/{servicio_id}")
    public CabeceraFactura obtenerFacturaPorId(@PathVariable Integer servicio_id){
        return cabeceraFacturaServicio.obtenerCabeceraFacturaPorId(servicio_id);
    }



}