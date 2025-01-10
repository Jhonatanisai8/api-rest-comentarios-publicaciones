package org.isai.api.comentario.services;

import org.isai.api.comentario.models.Publicacion;
import org.isai.api.comentario.repositories.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PublicacionService {
    @Autowired
    private PublicacionRepository repository;

    public Page<Publicacion> obtenerPublicaciones(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Publicacion obtenerPublicacionPorId(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro Publicacion con ID: " + id));
    }

    public Publicacion guardarPublicacion(Publicacion publicacion) {
        return repository.save(publicacion);
    }

    public Publicacion eliminarPublicacionPorId(Long id) {
        Publicacion publicacion = obtenerPublicacionPorId(id);
        repository.delete(publicacion);
        return publicacion;
    }
}
