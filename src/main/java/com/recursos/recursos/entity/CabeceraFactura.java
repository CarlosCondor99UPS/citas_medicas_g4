package com.recursos.recursos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cabeceraFactura")
public class CabeceraFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cabeceraFactura_id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date fecha;
    private double subtotal;
    private double totalIva;
    private double totalFactura;
    private boolean estado;
    @OneToMany(mappedBy = "cabeceraFactura", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"cabeceraFactura","persona"})
    private Set<DetalleFactura> detallesFacturas = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "persona_id")
    private Persona persona;

    public CabeceraFactura() {
    }
    public CabeceraFactura(Integer cabeceraFactura_id, String fechaString, double subtotal, double totalIva, double totalFactura, boolean estado) throws ParseException {
        this.cabeceraFactura_id = cabeceraFactura_id;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.fecha = dateFormat.parse(fechaString);
        this.subtotal = subtotal;
        this.totalIva = totalIva;
        this.totalFactura = totalFactura;
        this.estado = estado;
    }

    public Integer getCabeceraFactura_id() {
        return cabeceraFactura_id;
    }

    public void setCabeceraFactura_id(Integer cabeceraFactura_id) {
        this.cabeceraFactura_id = cabeceraFactura_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(double totalIva) {
        this.totalIva = totalIva;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Set<DetalleFactura> getDetallesFacturas() {
        return detallesFacturas;
    }

    public void setDetallesFacturas(Set<DetalleFactura> detallesFacturas) {
        this.detallesFacturas = detallesFacturas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
