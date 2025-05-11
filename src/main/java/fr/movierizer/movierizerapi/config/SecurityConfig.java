package fr.movierizer.movierizerapi.config;

import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import fr.movierizer.movierizerapi.services.UserServiceImpl;
import fr.movierizer.movierizerapi.model.User;

/* This is a class to configure the security of the application */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final UserServiceImpl userService;

    public SecurityConfig(UserServiceImpl userService) {
        this.userService = userService;
    }

     
    /**
     * The UserDetailsService interface is used to retrieve user information. It is called by the DaoAuthenticationProvider
     * to load the user information for the user. The loadUserByUsername method is called with the username as parameter and
     * it should return a UserDetails object.
     *
     * We use the UserServiceImpl to find the user by email and return a org.springframework.security.core.userdetails.User object
     * with the email as username, the password and the role of the user.
     *
     * @return a UserDetailsService object
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            User user = userService.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        };
    }

    /**
     * Creates an AuthenticationManager bean which is used by Spring Security to verify the authenticity of users.
     * 
     * @param authenticationConfiguration the configuration for the authentication manager
     * @return an AuthenticationManager bean
     * @throws Exception if an error occurs while creating the bean
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Creates a SecurityFilterChain bean which is used by Spring Security to secure the application.
     * 
     * This method disables CSRF protection, allows unauthenticated access to the "/register" and "/login" endpoints,
     * and requires authentication for all other requests. It also enables HTTP Basic authentication with default settings.
     * 
     * @param http the HttpSecurity object to configure
     * @return a SecurityFilterChain bean
     * @throws Exception if an error occurs while creating the bean
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.requestMatchers("/register", "/login").permitAll()
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
