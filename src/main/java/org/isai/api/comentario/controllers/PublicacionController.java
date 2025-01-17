package org.isai.api.comentario.controllers;

import org.isai.api.comentario.exceptions.ResourceNotFoundException;
import org.isai.api.comentario.models.Publicacion;
import org.isai.api.comentario.services.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class PublicacionController {

    @Autowired
    private PublicacionService service;

    @GetMapping("/publicaciones")
    public Page<Publicacion> listarPublicaciones(Pageable pageable) {
        return service.obtenerPublicaciones(pageable);
    }

    @PostMapping("/publicaciones")
    public Publicacion guardarPublicacion(@Valid @RequestBody Publicacion publicacion) {
        return service.guardarPublicacion(publicacion);
    }

    @PutMapping("/publicaciones/{id}")
    public Publicacion modificarPublicacion(@PathVariable Long id, @Valid @RequestBody Publicacion publicacion) {
        return service.modificarPublicacionID(id, publicacion);
    }

    @DeleteMapping("/publicaciones/{id}")
    public ResponseEntity<?> eliminarPublicacion(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.eliminarPublicacionPorId(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/publicaciones/{id}")
    public ResponseEntity<?> obtenerPublicacion(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.obtenerPublicacionPorId(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

}

