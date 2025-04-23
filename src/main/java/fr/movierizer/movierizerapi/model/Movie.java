package fr.movierizer.movierizerapi.model;

import java.lang.annotation.Inherited;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import javax.annotation.processing.Generated;
import java.util.Objects;
import jakarta.persistence.Table;


@Entity
@Table(name = "movies")
public class Movie {

	private @Id
	@GeneratedValue Long id; 
	private String title;
	private String description;
	private Integer grade;

	public Movie() {}

	public Movie(String title, String description, Integer grade) {
		this.title = title;
		this.description = description;
		this.grade = grade;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Movie))
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(this.id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.title, this.description, this.grade);
	}

	@Override
	public String toString() {
		return "Movie [id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", grade="
				+ this.grade + "]";
	}
}
