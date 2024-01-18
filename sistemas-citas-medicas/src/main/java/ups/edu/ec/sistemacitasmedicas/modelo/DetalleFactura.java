package ups.edu.ec.sistemacitasmedicas.modelo;

import jakarta.persistence.*;


@Entity
@Table(name = "detalleFactura")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detalleFactura_id;
    private String nombre;
    private int cantidad;
    private double precioUnitario;
    private double total;

    @ManyToOne
    @JoinColumn(name = "cabeceraFactura_id")
    private CabeceraFactura cabeceraFactura;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    public DetalleFactura(Integer detalleFactura_id, String nombre, int cantidad, double precioUnitario, double total) {
        this.detalleFactura_id = detalleFactura_id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
    }

    public DetalleFactura() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Integer getDetalleFactura_id() {
        return detalleFactura_id;
    }

    public void setDetalleFactura_id(Integer detalleFactura_id) {
        this.detalleFactura_id = detalleFactura_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public CabeceraFactura getCabeceraFactura() {
        return cabeceraFactura;
    }

    public void setCabeceraFactura(CabeceraFactura cabeceraFactura) {
        this.cabeceraFactura = cabeceraFactura;
    }



}
