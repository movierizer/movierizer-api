package fr.movierizer.movierizerapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.movierizer.movierizerapi.data.repository.UserRepository;

@Configuration
public class ApplicationConfiguration {
    private final UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);


    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Return a UserDetailsService implementation that uses the userRepository to
     * load a user by its username.
     * 
     * @return a UserDetailsService that loads a user by its username.
     */
    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Provides a BCryptPasswordEncoder bean for encrypting passwords.
     * 
     * @return a BCryptPasswordEncoder instance to be used for password encoding.
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides an AuthenticationManager bean that is retrieved from the
     * given AuthenticationConfiguration.
     * 
     * @param config the AuthenticationConfiguration from which to obtain the
     *               AuthenticationManager.
     * @return an AuthenticationManager instance.
     * @throws Exception if there is an error obtaining the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Provides an AuthenticationProvider bean that uses the userDetailsService to
     * load user information and the passwordEncoder to verify passwords.
     * 
     * @return an AuthenticationProvider instance.
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}