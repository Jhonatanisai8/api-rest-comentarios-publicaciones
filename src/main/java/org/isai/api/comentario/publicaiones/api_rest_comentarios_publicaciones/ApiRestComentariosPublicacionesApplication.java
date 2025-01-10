package org.isai.api.comentario.publicaiones.api_rest_comentarios_publicaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiRestComentariosPublicacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestComentariosPublicacionesApplication.class, args);
	}

}
