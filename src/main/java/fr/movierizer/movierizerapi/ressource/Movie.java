package fr.movierizer.movierizerapi.ressources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Movie {

    @RequestMapping("/")
	public String hello() {
		return "Hello World!";
	}
}
