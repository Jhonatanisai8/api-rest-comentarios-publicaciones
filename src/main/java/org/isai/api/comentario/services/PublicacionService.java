package org.isai.api.comentario.services;

import org.isai.api.comentario.exceptions.ResourceNotFoundException;
import org.isai.api.comentario.models.Publicacion;
import org.isai.api.comentario.repositories.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro Publicacion con ID: " + id));
    }

    public Publicacion guardarPublicacion(Publicacion publicacion) {
        return repository.save(publicacion);
    }

    public Publicacion eliminarPublicacionPorId(Long id) {
        Publicacion publicacion = obtenerPublicacionPorId(id);
        repository.delete(publicacion);
        return publicacion;
    }

    public Publicacion modificarPublicacionID(Long id, Publicacion request) {
        return repository
                .findById(id)
                .map(publicacion -> {
                    if (request.getDescripcion() != null || request.getDescripcion().isBlank()) {
                        publicacion.setDescripcion(request.getDescripcion());
                    }

                    if (request.getFechaActualizacion() != null) {
                        publicacion.setFechaActualizacion(request.getFechaActualizacion());
                    }

                    if (request.getFechaCreacion() != null) {
                        publicacion.setFechaCreacion(request.getFechaCreacion());
                    }

                    if (request.getTitulo() != null || request.getTitulo().isBlank()) {
                        publicacion.setTitulo(request.getTitulo());
                    }

                    if (request.getContenido() != null || request.getContenido().isBlank()) {
                        publicacion.setContenido(request.getContenido());
                    }
                    return repository.save(publicacion);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion no encontrada con ID: " + id));
    }

    public Publicacion eliminarPublicacionID(Long id) {
        return repository
                .findById(id)
                .map(publicacion -> {
                    repository.delete(publicacion);
                    return publicacion;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion no encontrada con ID: " + id));
    }
}
