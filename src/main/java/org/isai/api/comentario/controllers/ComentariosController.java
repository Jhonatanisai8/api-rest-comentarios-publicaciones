package org.isai.api.comentario.controllers;

import org.isai.api.comentario.models.Comentario;
import org.isai.api.comentario.services.ComentarioService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
public class ComentariosController {

    @Autowired
    private ComentarioService service;

    @GetMapping("/publicaciones/{idPublicacion}/comentarios")
    public Page<Comentario> obtenerComentariosPublicaciones(@PathVariable Long idPublicacion, Pageable pageable) {
        return service.obtenerComentarios(idPublicacion, pageable);
    }

    @PostMapping("/publicaciones/{idPublicacion}/comentarios")
    public Comentario guardarComentario(@PathVariable Long idPublicacion, @Valid @RequestBody Comentario comentario) {
        return service.guardarComentario(comentario, idPublicacion);
    }

}
