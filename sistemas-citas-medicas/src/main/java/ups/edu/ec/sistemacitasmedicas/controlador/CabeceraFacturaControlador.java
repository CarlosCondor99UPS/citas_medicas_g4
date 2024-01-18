package ups.edu.ec.sistemacitasmedicas.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ups.edu.ec.sistemacitasmedicas.modelo.CabeceraFactura;
import ups.edu.ec.sistemacitasmedicas.modelo.DetalleFactura;
import ups.edu.ec.sistemacitasmedicas.servicio.CabeceraFacturaServicio;
import ups.edu.ec.sistemacitasmedicas.servicio.DetalleFacturaServicio;

import java.util.List;

@RestController
@RequestMapping("/cabeceraFactura")
@CrossOrigin("*")
public class CabeceraFacturaControlador {
    @Autowired
    CabeceraFacturaServicio cabeceraFacturaServicio;
    @Autowired
    DetalleFacturaServicio detalleFacturaServicio;


    @GetMapping("/obtenerTotalFacturas")
    public ResponseEntity<String> obtenerTotalFacturas() throws Exception{

        List<CabeceraFactura> cabeceraFactura = cabeceraFacturaServicio.findAll();
        double sumaTotal = 0;

        if (cabeceraFactura.isEmpty()){
            throw new Exception("No existen facturas...");
        }

        sumaTotal = cabeceraFactura.stream().mapToDouble(dt -> dt.getTotalFactura()).sum();
        String mensaje = "El total de todas las facturas es de: "+sumaTotal;
        return ResponseEntity.ok(mensaje);

    }
    @GetMapping("/obtenerTotalServicios")
    public ResponseEntity<String> obtenerTotalServicios() throws Exception {

        List<DetalleFactura> detalleFactura = detalleFacturaServicio.findAll();
        double sumaTotal = 0;
        double sumaSubTotal = 0;

        if(detalleFactura.isEmpty()){
            throw new Exception("No existen Detalles Facturas Servicios...");
        }

        sumaTotal = detalleFactura.stream().mapToDouble(dt -> dt.getTotal()).sum();
        sumaSubTotal = detalleFactura.stream().mapToDouble(dt -> dt.getCabeceraFactura().getSubtotal()).sum();
        String mensaje = "La suma total de servicios contratados es de: "+sumaTotal+"\n"+
                         "La suma total de los subtotal son de: "+sumaSubTotal;

        return ResponseEntity.ok(mensaje);

    }

}
