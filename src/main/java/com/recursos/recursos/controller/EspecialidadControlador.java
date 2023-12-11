package com.recursos.recursos.controller;

import com.recursos.recursos.entity.Especialidad;
import com.recursos.recursos.entity.registro;
import com.recursos.recursos.services.EspecialidadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/especialidad")
@CrossOrigin("*")
public class EspecialidadControlador {
    private EspecialidadServicio especialidadServicio;

    @Autowired
    public EspecialidadControlador(EspecialidadServicio especialidadServicio) {
        this.especialidadServicio = especialidadServicio;
    }
    //LISTAR
    @GetMapping("/listar")
    public List<Especialidad> obtenerTodosLosItems() {
        return especialidadServicio.getEspecialidad();
    }

    //CREAR
    @PostMapping("/crear")
    public void crearEspecialidad(@RequestBody Especialidad especialidad) {
        especialidadServicio.insertarEspecialidad(especialidad);
    }
    //ACTUALIZAR
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarEspecialidad(@PathVariable("id") int id, @RequestBody Especialidad especialidadActualizado) {
        Especialidad especialidadActualizadoResultado = especialidadServicio.actualizarEspecialidad(id, especialidadActualizado);
        if (especialidadActualizadoResultado != null) {
            return ResponseEntity.ok("Catálogo actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();

        }
    }
    //ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarEspecialidad(@PathVariable("id") int id) {
        especialidadServicio.eliminarEspecialidad(id);
        return ResponseEntity.ok("Especialidad eliminada exitosamente");
    }
    //BUSCAR POR ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Especialidad> buscarPorId(@PathVariable("id") int id) {
        Especialidad especialEncontrado = especialidadServicio.buscarPorId(id);
        if (especialEncontrado != null) {
            return ResponseEntity.ok(especialEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
