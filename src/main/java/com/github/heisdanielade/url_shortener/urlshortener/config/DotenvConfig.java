package com.github.heisdanielade.url_shortener.urlshortener.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv() {
        Dotenv dotenv = Dotenv.configure()
                .directory("./")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        // Set system properties explicitly
//        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        // Debug logs
//        System.out.println("Loaded DB_URL: " + dotenv.get("DB_URL"));
//        System.out.println("Loaded DB_USERNAME: " + dotenv.get("DB_USERNAME"));
//        System.out.println("Loaded DB_PASSWORD: " + dotenv.get("DB_PASSWORD"));

        return dotenv;
    }
}
