package com.recursos.recursos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cita_id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaCita;
    private LocalTime hora;
    private boolean estado = false;
    @ManyToOne(fetch = FetchType.EAGER)
    private Persona persona;

    @ManyToOne
    private Medico medico;

    public Cita(Integer cita_id, Date fechaCitaDate, Time time, boolean estado) {
    }
    public Cita(Integer cita_id, LocalDate fechaCita, LocalTime hora, boolean estado) {
        this.cita_id = cita_id;
        this.fechaCita = fechaCita;
        this.hora = hora;
        this.estado = estado;
    }

    public Cita() {

    }

    public Integer getCita_id() {
        return cita_id;
    }

    public void setCita_id(Integer cita_id) {
        this.cita_id = cita_id;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
////HOLA MODIFICADO COMMIT ANTERIOR