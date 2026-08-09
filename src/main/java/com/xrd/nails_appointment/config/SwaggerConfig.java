package com.xrd.nails_appointment.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println(
                "Swagger UI: http://localhost:8080/swagger-ui/index.html"
        );
    }
}