package com.github.heisdanielade.url_shortener;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class UrlShortenerApplication {

	public static void main(String[] args) {
		System.out.println(System.lineSeparator() + "=== STARTING APPLICATION ===" + System.lineSeparator()); // Debug log

		Dotenv dotenv = Dotenv.configure()
				.directory("./")
				.ignoreIfMalformed()
				.ignoreIfMissing()
				.load();

		// Set system properties explicitly
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		System.out.println("DB_URL: " + System.getProperty("DB_URL"));
		System.out.println("DB_USERNAME: " + System.getProperty("DB_USERNAME"));
		System.out.println("DB_PASSWORD: " + System.getProperty("DB_PASSWORD"));

		SpringApplication.run(UrlShortenerApplication.class, args);
	}

}
