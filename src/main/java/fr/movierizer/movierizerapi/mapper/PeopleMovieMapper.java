package fr.movierizer.movierizerapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.movierizer.movierizerapi.data.DTO.CastDTO;
import fr.movierizer.movierizerapi.data.DTO.CrewDTO;
import fr.movierizer.movierizerapi.data.entities.Movie;
import fr.movierizer.movierizerapi.data.entities.People;
import fr.movierizer.movierizerapi.data.entities.People_movie;

@Component
public class PeopleMovieMapper {
    
        private static final Logger log = LoggerFactory.getLogger(PeopleMovieMapper.class);


    public List<People_movie> mapCastToPeopleMovie(List<CastDTO> cast, Movie movie) {
        return cast.stream().map(dto -> {
            People people = new People();
            people.setId(dto.getId());
            people.setName(dto.getName());
            people.setGender(dto.getGender());
            people.setProfile_path(dto.getProfile_path());
            people.setKnown_for_department(dto.getKnown_for_department());

            People_movie pm = new People_movie();
            pm.setMovie(movie);
            pm.setPeople(people);
            pm.setCharacter(dto.getCharacter());
            pm.setJob("Actor"); // acteur n’a pas de "job" sur TMDB
            return pm;
        }).collect(Collectors.toList());
    }

    public List<People_movie> mapCrewToPeopleMovie(List<CrewDTO> crew, Movie movie) {
        return crew.stream().map(dto -> {
            People people = new People();
            people.setId(dto.getId());
            people.setName(dto.getName());
            people.setGender(dto.getGender());
            people.setProfile_path(dto.getProfile_path());
            people.setKnown_for_department(dto.getKnown_for_department());

            People_movie pm = new People_movie();
            pm.setMovie(movie);
            pm.setPeople(people);
            pm.setJob(dto.getJob());
            return pm;
        }).collect(Collectors.toList());
    }
}

