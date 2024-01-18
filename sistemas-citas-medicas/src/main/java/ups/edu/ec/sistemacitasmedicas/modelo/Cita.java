package ups.edu.ec.sistemacitasmedicas.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

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
    private Date fechaCita;

    private Time hora;
    private boolean estado = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Persona persona;

    @ManyToOne
    private Medico medico;

    public Cita() {
    }

    public Cita(Integer cita_id, Date fechaCita, Time hora, boolean estado) {
        this.cita_id = cita_id;
        this.fechaCita = fechaCita;
        this.hora = hora;
        this.estado = estado;
    }

    public Integer getCita_id() {
        return cita_id;
    }

    public void setCita_id(Integer cita_id) {
        this.cita_id = cita_id;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
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