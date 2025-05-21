package fr.movierizer.movierizerapi.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/*This class is used to configure CORS to allowed the services who can talk with my API*/
@Configuration
@EnableWebSecurity
public class WebConfig implements WebMvcConfigurer {

    String origin_localhost = System.getenv("BACK_ALLOWED_ORIGINS_LOCALHOST");
    String origin_front = System.getenv("BACK_ALLOWED_ORIGINS_FRONT");
    String origin_deployement = System.getenv("BACK_ALLOWED_ORIGINS_DEPLOYEMENT");
    String origin_dev_deployement = System.getenv("BACK_ALLOWED_ORIGINS_DEV_DEPLOYEMENT");

    /**
     * CORS configuration to allow the React application to make requests to the API.
     * 
     * @param registry The CORS registry to add mappings to.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //For all the mapping
            .allowedOrigins(origin_localhost, origin_front , origin_deployement, origin_dev_deployement) //the URL of the react application
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true); //allowed the reponse to send cookies with identificals data   
    }
    
}