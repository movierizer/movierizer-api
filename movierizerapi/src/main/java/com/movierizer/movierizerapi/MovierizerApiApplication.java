package com.movierizer.api.movierizerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class MovierizerApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MovierizerApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello world !");
	}

}
