package com.amedida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación.
 * Al ejecutar este archivo, Spring Boot levanta un servidor web
 * (por defecto en http://localhost:8080) y registra automáticamente
 * todos los @RestController que existan en el proyecto.
 */
@SpringBootApplication
public class AmedidaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmedidaBackendApplication.class, args);
    }
}
