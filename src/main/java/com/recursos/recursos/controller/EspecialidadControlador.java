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

    @Autowired
    EspecialidadServicio especialidadServicio;

    //CREAR
    @PostMapping("/crear")
    public ResponseEntity<Especialidad> crearEspecialidad(@RequestBody Especialidad especialidad){
        return ResponseEntity.status(HttpStatus.CREATED).body(especialidadServicio.guardarEspecialidad(especialidad));
    }

    //LISTAR
    @GetMapping("/listar")
    public List<Especialidad> listarEspecialidades(){
        return especialidadServicio.listarEspecialidades();
    }

    //LISTAR POR ID
    @GetMapping("/buscar/{especialidad_id}")
    public ResponseEntity<Especialidad> listarEspecialidadesId(@PathVariable("especialidad_id") Integer especialidad_id){
        return ResponseEntity.status(HttpStatus.OK).body(especialidadServicio.obtenerEspecialidadPorId(especialidad_id));
    }

    //ELIMINAR
    @DeleteMapping("/eliminar/{especialidad_id}")
    public void eliminarEspecialidad(@PathVariable("especialidad_id") Integer especialidad_id){
        especialidadServicio.eliminarEspecialidadPorId(especialidad_id);
    }

    //ACTUALIZAR
    @PutMapping("/actualizar/{especialidad_id}")
    public ResponseEntity<Especialidad> actualizarEspecialidad(@PathVariable("especialidad_id") Integer especialidad_id, @RequestBody Especialidad especialidadActualizada) {
        Especialidad especialidad = especialidadServicio.actualizarEspecialidad(especialidad_id, especialidadActualizada);

        if (especialidad != null) {
            return ResponseEntity.status(HttpStatus.OK).body(especialidad);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
