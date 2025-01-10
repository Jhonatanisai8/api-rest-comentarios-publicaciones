package org.isai.api.comentario.services;

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
    private ComentarioRepository repository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    public Page<Comentario> obtenerComentarios(Long idPublicacion, Pageable pageable) {
        return repository.findByPublicacion(idPublicacion, pageable);
    }
    
}
