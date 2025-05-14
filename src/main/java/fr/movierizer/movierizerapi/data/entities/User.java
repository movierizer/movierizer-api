package fr.movierizer.movierizerapi.data.entities;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id; // Primary key and an id autoincrement but TODO implement a UID
    @Column(name = "username", nullable = false, unique = true)
    private String username; // the username is unique and he is choose by the user
    @Column(name = "email", nullable = false, unique = true)
    private String email; // email is unique and this is the email of the user 
    @Column(name = "password", nullable = false)
    private String password; // the password is encrypted and choose by the user
    @Column(name = "userlanguage")
    private String user_language; //the user language is english by default but the user can change this
    @Column(name = "profilepicture", columnDefinition = "TEXT")
    private String profile_picture; //the profile picture is a url of an image but it's not mandatory
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private String create_at; //this is a date when the user is created is profile it's automatically created by the database when the profile is created
    @Column(name = "role")
    private String role; //the role is user by default but the user and admin can change this role, the crerator is an admin
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt; //this is a date when the user is updated is profile it's automatically updated by the database when the profile is updated
    
    public User() {
    }

    public User(UUID id, String username, String email, String password, String userLanguage, String profilePicture,
    String create_at, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.user_language = userLanguage;
        this.profile_picture = profilePicture;
        this.create_at = create_at;
        this.role = role;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public User setUsername(String username) {
        this.username = username;
        return this;
    }
    public String getEmail() {
        return email;
    }
    public User setEmail(String email) {
        this.email = email;
        return this;
    }
    public String getPassword() {
        return password;
    }
    public User setPassword(String password) {
        this.password = password;
        return this;
    }
    public String getUser_language() {
        return user_language;
    }
    public void setUser_language(String userLanguage) {
        this.user_language = userLanguage;
    }
    public String getProfile_picture() {
        return profile_picture;
    }
    public void setProfile_picture(String profilePicture) {
        this.profile_picture = profilePicture;
    }
    public String getCreate_at() {
        return create_at;
    }
    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
 
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((user_language == null) ? 0 : user_language.hashCode());
        result = prime * result + ((profile_picture == null) ? 0 : profile_picture.hashCode());
        result = prime * result + ((create_at == null) ? 0 : create_at.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (user_language == null) {
            if (other.user_language != null)
                return false;
        } else if (!user_language.equals(other.user_language))
            return false;
        if (profile_picture == null) {
            if (other.profile_picture != null)
                return false;
        } else if (!profile_picture.equals(other.profile_picture))
            return false;
        if (create_at == null) {
            if (other.create_at != null)
                return false;
        } else if (!create_at.equals(other.create_at))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Users [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
                + ", userLanguage=" + user_language + ", profilePicture=" + profile_picture + ", create_at=" + create_at
                + ", role=" + role + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

}
