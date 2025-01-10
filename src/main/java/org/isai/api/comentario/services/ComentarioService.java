package org.isai.api.comentario.services;

import org.isai.api.comentario.exceptions.ResourceNotFoundException;
import org.isai.api.comentario.models.Comentario;
import org.isai.api.comentario.repositories.ComentarioRepository;
import org.isai.api.comentario.repositories.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.*;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    public Page<Comentario> obtenerComentarios(Long idPublicacion, Pageable pageable) {
        return comentarioRepository.findByPublicacion(idPublicacion, pageable);
    }

    public Comentario guardarComentario(Comentario comentario) {
        // buscamos la publicacion
        return publicacionRepository
                .findById(comentario.getPublicacion().getId())
                .map(publicacion -> {
                    comentario.setPublicacion(publicacion);
                    return comentarioRepository.save(comentario);
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Publicacion no encontrada con ID: " + comentario.getPublicacion().getId()));
    }
}
