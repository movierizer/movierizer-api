package fr.movierizer.movierizerapi.services;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.movierizer.movierizerapi.data.DTO.DtoMovieUser;
import fr.movierizer.movierizerapi.data.entities.Movie;
import fr.movierizer.movierizerapi.data.entities.User_movies;
import fr.movierizer.movierizerapi.data.repository.MovieRepository;
import fr.movierizer.movierizerapi.data.repository.User_movieRepository;

@Service 
public class UserMovieService {
    
    private static final Logger log = LoggerFactory.getLogger(UserMovieService.class);
    private final User_movieRepository user_movieRepository;
    private final MovieRepository movierepository;
    private final MovieServices movieServices;


    public UserMovieService(User_movieRepository user_movieRepository, MovieRepository movierepository, MovieServices movieServices) {
        this.user_movieRepository = user_movieRepository;
        this.movierepository = movierepository;
        this.movieServices = movieServices;
        
    }

    public DtoMovieUser getOneMovieWithUser (Movie movie, UUID iduser) {
        log.info("GET THE MOVIE WITH USER INFO");
        User_movies user_movie = user_movieRepository.findByUserIdAndMovieId(iduser, movie.getid());
        Movie movieReturned = movieServices.getOneMovie(movie.getid());
        return new DtoMovieUser(
                user_movie.getId(),
                user_movie.isWatchlist(),
                user_movie.getGrade(),
                movieReturned.getid(),
                movieReturned.getTitle(),
                movieReturned.getOverview(),
                movieReturned.getOriginal_title(),
                movieReturned.getRelease_date(),
                movieReturned.getPoster_path(),
                movieReturned.getBackdrop_path(),
                movieReturned.getBudget(),
                movieReturned.getRevenue(),
                movieReturned.getRuntime()
            );
    }


    public DtoMovieUser getOneMovieWithoutUser (Movie movie) {
        log.info("GET THE MOVIE WITHOUT USER INFO");
        Movie movieReturned = movieServices.getOneMovie(movie.getid());
        movierepository.save(movie);
        return new DtoMovieUser(
                movieReturned.getid(),
                movieReturned.getTitle(),
                movieReturned.getOverview(),
                movieReturned.getOriginal_title(),
                movieReturned.getRelease_date(),
                movieReturned.getPoster_path(),
                movieReturned.getBackdrop_path(),
                movieReturned.getBudget(),
                movieReturned.getRevenue(),
                movieReturned.getRuntime()
            );
    }

    public User_movies updateMovie(User_movies updateinfo, Long idMovie, UUID idUser) {
        log.info("UPDATE MOVIE");
        User_movies movieUpdated = user_movieRepository.findByUserIdAndMovieId(idUser, idMovie);
        if (movieUpdated == null) {
            User_movies newUserMovie = new User_movies();
            newUserMovie.setMovie_id(idMovie);
            newUserMovie.setGrade(updateinfo.getGrade());
            newUserMovie.setId(idUser);
            newUserMovie.setWatchlist(updateinfo.isWatchlist());
            return user_movieRepository.save(newUserMovie);
        }else{
            movieUpdated.setWatchlist(updateinfo.isWatchlist());
            movieUpdated.setGrade(updateinfo.getGrade());
            return user_movieRepository.save(movieUpdated);
        }

    }
}
