package org.isai.api.comentario;

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
