package ups.edu.ec.sistemacitasmedicas.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer especialidad_id;
    public String especialidad;
//, cascade = CascadeType.ALL
    @OneToMany(mappedBy = "especialidad")
    @JsonIgnore
    List<Medico> medicoEspecialidades = new ArrayList<>();


    public Especialidad(Integer especialidad_id, String especialidad) {
        this.especialidad_id = especialidad_id;
        this.especialidad = especialidad;
    }

    public Especialidad(Integer especialidad_id, String especialidad, List<Medico> medicoEspecialidades) {
        this.especialidad_id = especialidad_id;
        this.especialidad = especialidad;
        this.medicoEspecialidades = medicoEspecialidades;
    }

    public Especialidad(Integer especialidad_id) {
        this.especialidad_id = especialidad_id;
    }

    public Especialidad() {
    }

    public Integer getEspecialidad_id() {
        return especialidad_id;
    }

    public void setEspecialidad_id(Integer especialidad_id) {
        this.especialidad_id = especialidad_id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Medico> getMedicoEspecialidades() {
        return medicoEspecialidades;
    }

    public void setMedicoEspecialidades(List<Medico> medicoEspecialidades) {
        this.medicoEspecialidades = medicoEspecialidades;
    }
}
