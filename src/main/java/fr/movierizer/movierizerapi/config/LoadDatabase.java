package fr.movierizer.movierizerapi.config; /*problèmes de package */

import fr.movierizer.movierizerapi.model.Movie;
import fr.movierizer.movierizerapi.repository.MovieRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MovieRepository repository) {
        return args -> {
            log.info("Preloading" + repository.save(new Movie("Leon", "A man with glasses who killed people", 100)));
            log.info("Preloading" + repository.save(new Movie("seven", "What's in the box", 70)));
        };
    }
    
}
