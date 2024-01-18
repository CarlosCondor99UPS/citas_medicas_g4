package ups.edu.ec.sistemacitasmedicas.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ups.edu.ec.sistemacitasmedicas.modelo.Cita;
import ups.edu.ec.sistemacitasmedicas.repositorio.CitaRepositorio;
import ups.edu.ec.sistemacitasmedicas.servicio.CitaServicio;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServicioImpl implements CitaServicio {

    @Autowired
    CitaRepositorio citaRepositorio;


    @Override
    public Cita guardarCitaMedico(Cita cita, List<Cita> citaMedico) {
        return citaRepositorio.save(cita);
    }

    @Override
    public List<Cita> listarCitas() {
        return citaRepositorio.findAll();
    }

    @Override
    public Cita obtenerCitaPorId(Integer id) {
        Optional<Cita> citaOptional = citaRepositorio.findById(id);
        return citaOptional.orElse(null);
    }


    @Override
    public Optional<Cita> get(Integer id) {
        return citaRepositorio.findById(id);
    }




}
