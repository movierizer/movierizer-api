package fr.movierizer.movierizerapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.movierizer.movierizerapi.data.DTO.DtoMovieUser;
import fr.movierizer.movierizerapi.data.entities.Movie;
import fr.movierizer.movierizerapi.data.entities.People;
import fr.movierizer.movierizerapi.data.entities.People_movie;
import fr.movierizer.movierizerapi.data.entities.User_movies;
import fr.movierizer.movierizerapi.data.repository.MovieRepository;
import fr.movierizer.movierizerapi.data.repository.PeopleRepository;
import fr.movierizer.movierizerapi.data.repository.People_movieRepository;
import fr.movierizer.movierizerapi.data.repository.User_movieRepository;

@Service 
public class UserMovieService {
    
    private static final Logger log = LoggerFactory.getLogger(UserMovieService.class);
    private final User_movieRepository user_movieRepository;
    private final MovieServices movieServices;
    private final ApiService apiService;
    private final MovieRepository movierepository;
    private final PeopleRepository peopleRepository;
    private final People_movieRepository people_movieRepository;


    public UserMovieService(User_movieRepository user_movieRepository, MovieServices movieServices, ApiService apiService, MovieRepository movierepository, PeopleRepository peopleRepository, People_movieRepository people_movieRepository) {
        this.user_movieRepository = user_movieRepository;
        this.movieServices = movieServices;
        this.apiService = apiService;
        this.movierepository = movierepository;
        this.peopleRepository = peopleRepository;
        this.people_movieRepository = people_movieRepository;
    }

    public DtoMovieUser getOneMovieWithUser (Movie movie, UUID iduser) {
        log.info("GET THE MOVIE WITH USER INFO");
        User_movies user_movie = user_movieRepository.findByUserIdAndMovieId(iduser, movie.getid());
        Movie movieReturned = movieServices.getOneMovie(movie.getid());
        DtoMovieUser dtoMovieUser = new DtoMovieUser();
        return dtoMovieUser.fromMovieWithUser(movieReturned, user_movie);
    }


    public DtoMovieUser getOneMovieWithoutUser (Movie movie) {
        log.info("GET THE MOVIE WITHOUT USER INFO");
        Movie movieReturned = movieServices.getOneMovie(movie.getid());
        DtoMovieUser dtoMovieUser = new DtoMovieUser();
        return dtoMovieUser.fromMovieWithoutUser(movieReturned);
    }

    public User_movies updateMovie(DtoMovieUser updateinfo, Long idMovie, UUID idUser) {
        log.info("UPDATE OR SAVE MOVIE");
        User_movies movieUpdated = user_movieRepository.findByUserIdAndMovieId(idUser, idMovie);
        if (movieUpdated == null) {
            User_movies newUserMovie = new User_movies();
            newUserMovie.setMovie_id(idMovie);
            newUserMovie.setGrade(updateinfo.getGrade());
            newUserMovie.setId(idUser);
            newUserMovie.setWatchlist(updateinfo.getWatchlist());
            Movie newMovie = new Movie(idMovie, updateinfo.getTitle(), updateinfo.getOverview(), updateinfo.getOriginal_title(), updateinfo.getRelease_date(), updateinfo.getPoster_path(), updateinfo.getBackdrop_path(), updateinfo.getBudget(), updateinfo.getRevenue(), updateinfo.getRuntime(), null, null);
            movierepository.save(newMovie);
            for (int i = 0; i < updateinfo.getCredits().size(); i++) { 
                People newPeople = new People(updateinfo.getCredits().get(i).getPeople());
                People_movie newPeople_movie = new People_movie();
                newPeople_movie.setMovie(newMovie);
                newPeople_movie.setPeople(newPeople);
                newPeople_movie.setCharacter(updateinfo.getCredits().get(i).getCharacter());
                newPeople_movie.setJob(updateinfo.getCredits().get(i).getJob());
                peopleRepository.save(newPeople);
                people_movieRepository.save(newPeople_movie);
            }
            return user_movieRepository.save(newUserMovie);  
        }else{
            movieUpdated.setWatchlist(updateinfo.getWatchlist());
            movieUpdated.setGrade(updateinfo.getGrade());
            return user_movieRepository.save(movieUpdated);
        }

    }

    public List<DtoMovieUser> getWatchlist(UUID id) {
        log.info("GET WATCHLIST");
        List<User_movies> watchlist = user_movieRepository.findByUserIdAndWatchlist(id, "watchlist");
        if (watchlist.isEmpty()) {
            throw new RuntimeException("Watchlist is empty");
        }else{
            ArrayList<DtoMovieUser> dtoWatchlist = new ArrayList<DtoMovieUser>();
            for (User_movies user_movie : watchlist) {
                Movie movie = movieServices.getOneMovie(user_movie.getMovie_id());
                dtoWatchlist.add(new DtoMovieUser().fromMovieWithUser(movie, user_movie));
            }
            return dtoWatchlist;
        }
    }

    public List<DtoMovieUser> getCollection(UUID id) {
        log.info("GET COLLECTION");
        List<User_movies> collection = user_movieRepository.findByUserIdAndWatchlistNotNull(id);
        if (collection.isEmpty()) {
            throw new RuntimeException("Watchlist is empty");
        }else{
            ArrayList<DtoMovieUser> dtoWatchlist = new ArrayList<DtoMovieUser>();
            for (User_movies user_movie : collection) {
                Movie movie = movieServices.getOneMovie(user_movie.getMovie_id());
                dtoWatchlist.add(new DtoMovieUser().fromMovieWithUser(movie, user_movie));
            }
            return dtoWatchlist;
        }
    }


}
