package fr.movierizer.movierizerapi.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*This class is my movies entity who represent a movie in my database*/
@Entity
@Table(name = "movies")
public class Movie {

	//TODO vu d'ensemnble a medifier a terme 
	@Column(name = "id")
	private @Id Long id; //this id is the primary key and we use the TMDB id 
	@Column(name = "title") 
	private String title; //title of the movie in the user language
	@Column(name = "overview", columnDefinition = "TEXT")
	private String overview;//the synopsis of the movie
	@Column(name = "original_title")
	private String original_title; //title of the movie in the original language
	@Column(name = "release_date")
	private String release_date; 
	@Column(name = "poster_path")
	private String poster_path; //the url of the poster of the movie in the TMDB API
	@Column(name = "backdrop_path")
	private String backdrop_path; //the url of the backdrop of the movie in the TMDB API
	@Column(name = "budget")
	private int budget; //the budget to make the movie
	@Column(name = "revenue")
	private int revenue; //all the money made by the movie
	@Column(name = "runtime")
	private int runtime; //the duration of the movie in minutes
	@Column(name = "country")
	private String country;
	@Column(name = "url_trailer")
	private String url_trailer;


	/*Default constructor*/
	public Movie() {}

	public Movie(String title, Integer grade) {
		this.title = title;
	}
	
    public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getOriginal_title() {
		return original_title;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getBackdrop_path() {
		return backdrop_path;
	}

	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (overview == null) {
			if (other.overview != null)
				return false;
		} else if (!overview.equals(other.overview))
			return false;
		if (original_title == null) {
			if (other.original_title != null)
				return false;
		} else if (!original_title.equals(other.original_title))
			return false;
		if (release_date == null) {
			if (other.release_date != null)
				return false;
		} else if (!release_date.equals(other.release_date))
			return false;
		if (poster_path == null) {
			if (other.poster_path != null)
				return false;
		} else if (!poster_path.equals(other.poster_path))
			return false;
		if (backdrop_path == null) {
			if (other.backdrop_path != null)
				return false;
		} else if (!backdrop_path.equals(other.backdrop_path))
			return false;
		if (budget != other.budget)
			return false;
		if (revenue != other.revenue)
			return false;
		if (runtime != other.runtime)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((overview == null) ? 0 : overview.hashCode());
		result = prime * result + ((original_title == null) ? 0 : original_title.hashCode());
		result = prime * result + ((release_date == null) ? 0 : release_date.hashCode());
		result = prime * result + ((poster_path == null) ? 0 : poster_path.hashCode());
		result = prime * result + ((backdrop_path == null) ? 0 : backdrop_path.hashCode());
		result = prime * result + budget;
		result = prime * result + revenue;
		result = prime * result + runtime;
		return result;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", description=" + overview 
				+ ", originalTitle=" + original_title + ", releaseDate=" + release_date + ", posterPath=" + poster_path
				+ ", backdropPath=" + backdrop_path + ", budget=" + budget + ", revenue=" + revenue + ", runtime="
				+ runtime + "]";
	}


}
