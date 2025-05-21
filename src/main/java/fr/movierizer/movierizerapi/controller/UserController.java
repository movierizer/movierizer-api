package fr.movierizer.movierizerapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.movierizer.movierizerapi.data.entities.User;
import fr.movierizer.movierizerapi.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
    
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> getOneUser(@RequestHeader String authorization){ 
        log.info("GET ONE USER MAPPING");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            User userToReturned = userService.getOneUser(token);
            return ResponseEntity.ok(userToReturned);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new User()); //TODO see to return maybe a string or something else 
        }
        
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestHeader String authorization, @RequestBody User updateUser){
        log.info("UPDATE USER MAPPING");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            userService.updateOneUSer(token, updateUser);
            return ResponseEntity.ok(updateUser);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new User()); //TODO see to return maybe a string or something else 
        }
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteOneUser(@RequestHeader String authorization) {
        log.info("DELETE ONE USER MAPPING");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            userService.deleteOneUser(token);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/token")
    public ResponseEntity<User> updateToken(@RequestHeader String authorization, @RequestBody String tokenTMDB){
        log.info("UPDATE USER TOKEN MAPPING");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            String finalTokenTMDB = tokenTMDB.replaceAll("^\"|\"$", ""); // delete the " " around the token
            User userToUpdate = userService.updateToken(token, finalTokenTMDB);
            return ResponseEntity.ok(userToUpdate);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new User()); //TODO see to return maybe a string or something else 
        }
    }
}