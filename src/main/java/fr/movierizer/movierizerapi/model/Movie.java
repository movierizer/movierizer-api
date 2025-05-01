package fr.movierizer.movierizerapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*This class is my movies entity who represent a movie in my database*/
@Entity
@Table(name = "movies")
public class Movie {


	private @Id
	@GeneratedValue Long id; 
	private String title;
	private String description;
	private Integer grade;
	private String originalTitle;
	private String releaseDate;
	private String posterPath;
	private String backdropPath;
	private int budget;
	private int revenue;
	private int runtime;

	/*Default constructor*/
	public Movie() {}

	public Movie(String title, String description, Integer grade){
		this.title = title;
		this.description = description;
		this.grade = grade;
	}

	public Movie(Long id, String title, String description, Integer grade, String originalTitle, String releaseDate,
	String posterPath, String backdropPath, int budget, int revenue, int runtime) {
	this.id = id;
	this.title = title;
	this.description = description;
	this.grade = grade;
	this.originalTitle = originalTitle;
	this.releaseDate = releaseDate;
	this.posterPath = posterPath;
	this.backdropPath = backdropPath;
	this.budget = budget;
	this.revenue = revenue;
	this.runtime = runtime;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (originalTitle == null) {
			if (other.originalTitle != null)
				return false;
		} else if (!originalTitle.equals(other.originalTitle))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (posterPath == null) {
			if (other.posterPath != null)
				return false;
		} else if (!posterPath.equals(other.posterPath))
			return false;
		if (backdropPath == null) {
			if (other.backdropPath != null)
				return false;
		} else if (!backdropPath.equals(other.backdropPath))
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
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((originalTitle == null) ? 0 : originalTitle.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((posterPath == null) ? 0 : posterPath.hashCode());
		result = prime * result + ((backdropPath == null) ? 0 : backdropPath.hashCode());
		result = prime * result + budget;
		result = prime * result + revenue;
		result = prime * result + runtime;
		return result;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", description=" + description + ", grade=" + grade
				+ ", originalTitle=" + originalTitle + ", releaseDate=" + releaseDate + ", posterPath=" + posterPath
				+ ", backdropPath=" + backdropPath + ", budget=" + budget + ", revenue=" + revenue + ", runtime="
				+ runtime + "]";
	}
}
