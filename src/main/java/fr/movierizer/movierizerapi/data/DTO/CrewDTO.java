package fr.movierizer.movierizerapi.data.DTO;

public class CrewDTO {
    
    private Long id;
    private String name;
    private String profile_path;
    private Integer gender;
    private String job;

    public CrewDTO() {
    }

    public CrewDTO(Long id, String name, String profile_path, Integer gender, String job) {
        this.id = id;
        this.name = name;
        this.profile_path = profile_path;
        this.gender = gender;
        this.job = job;
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
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }

    

}
