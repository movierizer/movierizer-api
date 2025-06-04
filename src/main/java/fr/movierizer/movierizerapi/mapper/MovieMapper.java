package fr.movierizer.movierizerapi.mapper;

import org.springframework.stereotype.Component;

import fr.movierizer.movierizerapi.data.DTO.MovieDTO;
import fr.movierizer.movierizerapi.data.entities.Movie;

@Component
public class MovieMapper {
    
    public Movie toEntity(MovieDTO dto) {
        Movie movie = new Movie();
        movie.setid(dto.getId());
        movie.setTitle(dto.getTitle());
        movie.setOverview(dto.getOverview());
        movie.setRelease_date(dto.getRelease_date());
        movie.setPoster_path(dto.getPoster_path());
        movie.setBackdrop_path(dto.getBackdrop_path());
        movie.setOriginal_title(dto.getOriginal_title());
        movie.setBudget(dto.getBudget());
        movie.setRevenue(dto.getRevenue());
        movie.setRuntime(dto.getRuntime());
        return movie;
    }
}
