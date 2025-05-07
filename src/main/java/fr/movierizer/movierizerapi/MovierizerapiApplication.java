package fr.movierizer.movierizerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*This class is the main class of my application */
@SpringBootApplication
public class MovierizerapiApplication {

	/**
	 * This is the main class of the application, it is used to start the Spring Boot application.
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MovierizerapiApplication.class, args);
	}

}
