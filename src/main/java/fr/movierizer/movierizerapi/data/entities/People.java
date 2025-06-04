package fr.movierizer.movierizerapi.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "people")
public class People {
    
    @Column(name = "id")
    private @Id Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "profile_path")
    private String profile_path;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "known_for_department")
    private String known_for_department;

    public People() {}

    public People(Long id, String name, String profile_path, Integer gender, String known_for_department) {
        this.id = id;
        this.name = name;
        this.profile_path = profile_path;
        this.gender = gender;
        this.known_for_department = known_for_department;
    }

    public People(People people) {
        this.id = people.getId();
        this.name = people.getName();
        this.profile_path = people.getProfile_path();
        this.gender = people.getGender();
        this.known_for_department = people.getKnown_for_department();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
    }

    
}
