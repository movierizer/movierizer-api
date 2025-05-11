package fr.movierizer.movierizerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncoderConfig {
    
    /**
     * A PasswordEncoder that uses the BCrypt password hashing algorithm.
     * It is used to encode passwords when registering a new user and
     * to verify passwords when logging in.
     *
     * @return a PasswordEncoder object
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
