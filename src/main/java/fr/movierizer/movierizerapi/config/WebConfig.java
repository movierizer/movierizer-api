package fr.movierizer.movierizerapi.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.context.annotation.Configuration;

/*This class is used to configure CORS to allowed the services who can talk with my API*/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * CORS configuration to allow the React application to make requests to the API.
     * 
     * @param registry The CORS registry to add mappings to.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //For all the mapping
            .allowedOrigins(System.getenv("BACK_ALLOWED_ORIGINS_LOCALHOST"),System.getenv("BACK_ALLOWED_ORIGINS_FRONT")) //the URL of the react application
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true); //allowed the reponse to send cookies with identificals data
    }
    
}