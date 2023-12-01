package com.example.toyshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ToyShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToyShopApplication.class);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:8080");
                registry.addMapping("/**").allowedOrigins("http://localhost:8081");
                registry.addMapping("/**").allowedOrigins("http://localhost:8082");
//                registry.addMapping("/**").allowedOrigins("http://0.0.0.0:8080");
//                registry.addMapping("/**").allowedOrigins("http://127.0.0.1:8080");
            }
        };
    }
}
