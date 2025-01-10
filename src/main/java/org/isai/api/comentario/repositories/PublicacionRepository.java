package org.isai.api.comentario.repositories;

import org.isai.api.comentario.models.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository
        extends JpaRepository<Publicacion, Long> {

}