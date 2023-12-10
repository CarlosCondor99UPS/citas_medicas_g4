package com.recursos.recursos.controller;

import com.recursos.recursos.entity.registro;
import com.recursos.recursos.services.recursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
@RestController
@RequestMapping("/registro")
public class registroController {

    private recursoService recService;
    @Autowired
    public registroController(recursoService recService) {
        this.recService = recService;
    }

    @GetMapping("/todos")
    public List<registro> obtenerTodosLosItems() {

        return recService.getListaTodo();
    }

    @PostMapping("/crear")
    public void crearRegistro(@RequestBody registro rec) {
        recService.insertarRegistro(rec);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarRegistro(@PathVariable("id") int id, @RequestBody registro usuarioActualizado) {
        registro usuarioActualizadoResultado = recService.actualizarRegistro(id, usuarioActualizado);
        if (usuarioActualizadoResultado != null) {
            return ResponseEntity.ok("Usuario actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarRegistro(@PathVariable("id") int id) {
        recService.eliminarRegistro(id);
        return ResponseEntity.ok("Usuario eliminado exitosamente");
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<registro> buscarPorId(@PathVariable("id") int id) {
        registro usuarioEncontrado = recService.buscarPorId(id);
        if (usuarioEncontrado != null) {
            return ResponseEntity.ok(usuarioEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
