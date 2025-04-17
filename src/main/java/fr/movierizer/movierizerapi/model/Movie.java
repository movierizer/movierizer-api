package fr.movierizer.movierizerapi.model;

import java.lang.annotation.Inherited;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import javax.annotation.processing.Generated;
import java.util.Objects;

@Entity
public class Movie {

	private @Id
	@GeneratedValue Long isan; 
	private String title;
	private String description;
	private Integer grade;

	public Movie() {}

	public Movie(String title, String description, Integer grade) {
		this.title = title;
		this.description = description;
		this.grade = grade;
	}

    public Long getIsan() {
		return isan;
	}

	public void setIsan(Long isan) {
		this.isan = isan;
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
		return Objects.equals(this.isan, other.isan);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.isan, this.title, this.description, this.grade);
	}

	@Override
	public String toString() {
		return "Movie [isan=" + this.isan + ", title=" + this.title + ", description=" + this.description + ", grade="
				+ this.grade + "]";
	}
}
