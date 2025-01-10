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

    public Comentario guardarComentario(Comentario comentario, Long idPublicacion) {
        // buscamos la publicacion
        return publicacionRepository
                .findById(idPublicacion)
                .map(publicacion -> {
                    comentario.setPublicacion(publicacion);
                    return comentarioRepository.save(comentario);
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Publicacion no encontrada con ID: " + idPublicacion));
    }

    public Comentario actualizarComentario(Comentario request, Long idPublicacion, Long idComentario) {
        if (!publicacionRepository.existsById(idPublicacion)) {
            throw new ResourceNotFoundException(
                    "Publicacion no encontrada con ID: " + idPublicacion);
        }

        return comentarioRepository
                .findById(idComentario)
                .map(comentario -> {
                    if (request.getTexto() != null) {
                        comentario.setTexto(request.getTexto());
                    }
                    return comentarioRepository.save(comentario);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado con ID: " + idComentario));
    }

    public Comentario eliminarPublicacionID(Long idComentario, Long idPublicacion) {
        return comentarioRepository
                .findByIdAndPublicacionId(idComentario, idPublicacion)
                .map(comentario -> {
                    comentarioRepository.delete(comentario);
                    return comentario;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado con ID: " + idComentario
                        + " y Publicacion con ID: " + idPublicacion));
    }
}
