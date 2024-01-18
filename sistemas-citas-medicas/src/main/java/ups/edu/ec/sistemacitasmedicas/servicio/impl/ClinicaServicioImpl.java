package ups.edu.ec.sistemacitasmedicas.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ups.edu.ec.sistemacitasmedicas.modelo.Clinica;
import ups.edu.ec.sistemacitasmedicas.repositorio.ClinicaRepositorio;
import ups.edu.ec.sistemacitasmedicas.servicio.ClinicaServicio;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicaServicioImpl implements ClinicaServicio {


    @Autowired
    ClinicaRepositorio clinicaRepositorio;


    @Override
    public Clinica guardarprescripcion(Clinica clinica, List<Clinica> clinicaPersona) {
        return clinicaRepositorio.save(clinica);
    }

    @Override
    public List<Clinica> listarClinica() {
        return clinicaRepositorio.findAll();
    }

    @Override
    public Clinica obtenerClinicaPorId(Integer clinica_id) {
        Optional<Clinica> citaOptional = clinicaRepositorio.findById(clinica_id);
        return citaOptional.orElse(null);
    }


    @Override
    public Optional<Clinica> get(Integer clinica_id) {
        return clinicaRepositorio.findById(clinica_id);
    }
}
