package com.recursos.recursos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medico_id;
    private String telenoConsultorio;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Persona persona;
    @ManyToOne
    @JsonIgnore
    private Especialidad especialidad;
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Cita> medicoCita = new ArrayList<>();

    public Medico(Integer medico_id,String telenoConsultorio) {
        this.medico_id= medico_id;
        this.telenoConsultorio= telenoConsultorio;
    }

    public Medico(Integer medico_id) {
        this.medico_id = medico_id;
    }

    public Medico() {
    }

    public Integer getMedico_id() {
        return medico_id;
    }

    public void setMedico_id(Integer medico_id) {
        this.medico_id = medico_id;
    }

    public String getTelenoConsultorio() {
        return telenoConsultorio;
    }

    public void setTelenoConsultorio(String telenoConsultorio) {
        this.telenoConsultorio = telenoConsultorio;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public List<Cita> getMedicoCita() {
        return medicoCita;
    }

    public void setMedicoCita(List<Cita> medicoCita) {
        this.medicoCita = medicoCita;
    }
}
