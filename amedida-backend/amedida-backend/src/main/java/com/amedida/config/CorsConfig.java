package com.amedida.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Sin esto, el navegador BLOQUEA las peticiones que hace tu HTML
 * (abierto como archivo o en otro puerto) hacia este backend,
 * por seguridad (política de "same-origin"). Esta clase le dice
 * al servidor: "acepta peticiones desde cualquier origen mientras
 * desarrollamos" (en producción esto se restringe al dominio real).
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
