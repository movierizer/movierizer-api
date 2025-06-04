package fr.movierizer.movierizerapi.data.DTO;

public class CastDTO {
    
    private Long id;
    private String name;
    private String profile_path;
    private Integer gender;
    private String character;
    private String known_for_department;
    
    public CastDTO() {
    }

    
    public CastDTO(Long id, String name, String profile_path, Integer gender, String character, String known_for_department) {
        this.id = id;
        this.name = name;
        this.profile_path = profile_path;
        this.gender = gender;
        this.character = character;
        this.known_for_department = known_for_department;
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
    public String getCharacter() {
        return character;
    }
    public void setCharacter(String character) {
        this.character = character;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
    }

    
}
