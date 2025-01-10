package org.isai.api.comentario.repositories;

import org.isai.api.comentario.models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository
        extends JpaRepository<Comentario, Long> {

}
