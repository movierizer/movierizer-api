package fr.movierizer.movierizerapi.config; 

import fr.movierizer.movierizerapi.model.Movie;
import fr.movierizer.movierizerapi.repository.MovieRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*This is a class to load the database with some movies when starting the app */
@Configuration
public class LoadDatabase {

    //logger use to print message in the console
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    //Bean mean the fonction will be execute when starting the app
    @Bean
    CommandLineRunner initDatabase(MovieRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Movie movie1 = new Movie("Léon", "A man with glasses who killed people", 100);
                repository.save(movie1);
                log.info("Preloading: " + movie1);
            }
        };
    }
    
}
