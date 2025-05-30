package fr.movierizer.movierizerapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.movierizer.movierizerapi.data.DTO.DtoMovieUser;
import fr.movierizer.movierizerapi.data.entities.User;
import fr.movierizer.movierizerapi.data.entities.User_movies;
import fr.movierizer.movierizerapi.services.UserMovieService;
import fr.movierizer.movierizerapi.services.UserService;

@RestController
@RequestMapping("/lists")
public class UserMovieController {
    
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);
    private final UserMovieService usermovieservices;
    private final UserService userService;

    public UserMovieController(UserMovieService usermovieservices, UserService userService) {
        this.userService = userService;
        this.usermovieservices = usermovieservices;
        
    }

    @GetMapping("/watchlist")
    public ResponseEntity<List<DtoMovieUser>> getWatchlist(@RequestHeader String authorization) {
        log.info("GET WATCHLIST MAPPING");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            User movieUser = userService.getOneUser(token);
            List<DtoMovieUser> watchlist = usermovieservices.getWatchlist(movieUser.getId()); // TODO see to correctly handle if getwatchlist return empty
            return ResponseEntity.ok(watchlist);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); //TODO return somathing more usefull to interpret 
        }
    }

    @GetMapping("/collection")
    public ResponseEntity<List<DtoMovieUser>> getCollection(@RequestHeader String authorization) {
        log.info("GET COLLECTION MAPPING");
         if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            User movieUser = userService.getOneUser(token);
            List<DtoMovieUser> watchlist = usermovieservices.getCollection(movieUser.getId()); // TODO see to correctly handle if getCollection return empty
            return ResponseEntity.ok(watchlist);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); //TODO return somathing more usefull to interpret 
        }
    }

}
