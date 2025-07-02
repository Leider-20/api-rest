package com.lsms.restapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /** Esta clase es para habilitar solicitudes relacionadas a la política de seguridad del navegador llamada CORS (Cross-Origin Resource Sharing).
    Esta política bloquea las solicitudes que se realizan desde un origen distinto al que sirve el recurso solicitado,
     a menos que el servidor permita explícitamente dichas solicitudes.*/

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://192.168.1.100:5500")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}

