/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.safaricom.hackathon.BackendInterviewQuestion.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import ke.safaricom.hackathon.BackendInterviewQuestion.entities.Movies;
import ke.safaricom.hackathon.BackendInterviewQuestion.entities.Role;
import ke.safaricom.hackathon.BackendInterviewQuestion.entities.User;
import ke.safaricom.hackathon.BackendInterviewQuestion.exceptions.ResourceNotFoundException;
import ke.safaricom.hackathon.BackendInterviewQuestion.payloads.MovieRequest;
import ke.safaricom.hackathon.BackendInterviewQuestion.payloads.MovieResponse;
import ke.safaricom.hackathon.BackendInterviewQuestion.repositories.MoviesRepository;
import ke.safaricom.hackathon.BackendInterviewQuestion.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author techsavanna
 */
@RestController
@RequestMapping("/api")
public class MoviesController {

    @Autowired
    private MoviesRepository movieRepository;
    
    UserService service;
    
    @PostMapping("/movies/addmovie")
    public Movies addMovie(@Valid @RequestBody MovieRequest addmovie) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        String title = addmovie.getTitle();
        String description = addmovie.getDescription();
        String recommendation = addmovie.getRecommendation();
        Integer rating = addmovie.getRating();
        String watch_flag = addmovie.getWatch_flag();

        Movies movie = new Movies(title, description, recommendation, rating, username,watch_flag);
        movieRepository.save(movie);

        return movie;
    }

    @GetMapping("/movies")
    public List<Movies> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/movies/get/{username}")
    public Optional<Movies> getMoviesByUser(@PathVariable(value = "username") String username) throws ResourceNotFoundException {
        Optional<Movies> moviebyuser = movieRepository.findByUsername(username);
        if (!moviebyuser.isPresent()) {
            throw new ResourceNotFoundException("Movie not found under  this username : " + username);
        }
        return moviebyuser;
    }

    @GetMapping("/movies/getbywatchflag/{watch_flag}")
    public Optional<Movies> getMoviesByWatchflag(@PathVariable(value = "watch_flag") String watch_flag) throws ResourceNotFoundException {
        Optional<Movies> moviebywatchflag = movieRepository.findByWatchFlag(watch_flag);
        if (!moviebywatchflag.isPresent()) {
            throw new ResourceNotFoundException("Movie not found under  this username : " + watch_flag);
        }
        return moviebywatchflag;
    }
    
    
    @DeleteMapping("/movies/delete/{title}")
    public MovieResponse deleteMovie(@PathVariable String title) {
        Movies titletodelete = movieRepository.findByTitle(title);
        
        System.out.println("title is"+title);
        movieRepository.delete(titletodelete);
        return new MovieResponse(true, "Deleted successifully");
    }

    @PutMapping("/movies/update/{title}")
    public Movies putMovie(@PathVariable String title, @Valid @RequestBody MovieRequest updatemovie) {
        // String msisdn=structurepayload.getMsisdn();
        Movies movieupdate = movieRepository.findByTitle(title);

        movieupdate.setTitle(updatemovie.getTitle());
        movieupdate.setDescription(updatemovie.getDescription());
        movieupdate.setRecommendation(updatemovie.getRecommendation());
        movieupdate.setRating(updatemovie.getRating());
        return movieupdate;
    }
    
    @PostMapping("/movies/addUser")
    public User signUpUser(@Valid @RequestBody User user) {
        
        String username = user.getUsername();
        String password = user.getPassword();

        service.save(new User(
                   username, //username
                   password, //password
Arrays.asList(new Role("USER"), new Role("ACTUATOR")),//roles 
                    true//Active
            ));

        return null;
    }
}
