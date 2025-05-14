package fr.movierizer.movierizerapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.movierizer.movierizerapi.data.entities.Movie;
import fr.movierizer.movierizerapi.data.repository.MovieRepository;


@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(MovieRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Movie("Léon", 100)));
      log.info("Preloading " + repository.save(new Movie("The Matrix", 80)));
    };
  }
}
