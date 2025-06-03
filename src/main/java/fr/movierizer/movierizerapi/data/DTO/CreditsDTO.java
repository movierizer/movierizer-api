package fr.movierizer.movierizerapi.data.DTO;

import java.util.List;

import fr.movierizer.movierizerapi.data.entities.People_movie;

public class CreditsDTO {
    
	private List<CastDTO> cast;
	private List<CrewDTO> crew;


    public CreditsDTO() {}
    
    public CreditsDTO(List<CastDTO> cast, List<CrewDTO> crew) {
        this.cast = cast;
        this.crew = crew;
    }

    public List<CastDTO> getCast() {
		return cast;
	}

	public void setCast(List<CastDTO> cast) {
		this.cast = cast;
	}

	public List<CrewDTO> getCrew() {
		return crew;
	}

	public void setCrew(List<CrewDTO> crew) {
		this.crew = crew;
	}

}
