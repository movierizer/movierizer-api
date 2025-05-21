package fr.movierizer.movierizerapi.config.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


/* This is a class to configure the security of the application */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    String origin_localhost = System.getenv("BACK_ALLOWED_ORIGINS_LOCALHOST");
    String origin_front = System.getenv("BACK_ALLOWED_ORIGINS_FRONT");
    String origin_deployement = System.getenv("BACK_ALLOWED_ORIGINS_DEPLOYEMENT");
    String origin_dev_deployement = System.getenv("BACK_ALLOWED_ORIGINS_DEV_DEPLOYEMENT");

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider){
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Configures the security filter chain to:
     * - Disable CSRF
     * - Allow requests to /auth/** without authentication
     * - Require authentication for all other requests
     * - Use HTTP Basic authentication
     * - Set the session management policy to STATELESS
     * - Use the custom authentication provider
     * - Add the JwtAuthenticationFilter before the UsernamePasswordAuthenticationFilter
     * 
     * @param http the HttpSecurity object to configure
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.csrf(AbstractHttpConfigurer::disable)
          .authorizeHttpRequests(auth -> 
                    auth.requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
          .httpBasic(Customizer.withDefaults())
          .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .authenticationProvider(authenticationProvider)
          .cors(cors -> cors.configurationSource(corsConfigurationSource()))
          .csrf(csrf -> csrf.disable())
          .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(origin_localhost, origin_front, origin_deployement, origin_dev_deployement));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;  
    }

}
