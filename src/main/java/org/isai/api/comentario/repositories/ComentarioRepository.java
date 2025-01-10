package org.isai.api.comentario.repositories;

import java.util.Optional;

import org.isai.api.comentario.models.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository
        extends JpaRepository<Comentario, Long> {
    Page<Comentario> findByPublicacion(Long idPublicacion, Pageable page);

    Optional<Comentario> findByIdAndPublicacionId(Long comentarioID, Long publicacionID);
}
