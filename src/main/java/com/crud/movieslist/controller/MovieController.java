package com.crud.movieslist.controller;

import com.crud.movieslist.domain.Movie;
import com.crud.movieslist.service.MovieServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MovieController {

    private final MovieServiceImpl movieServiceImpl;

    public MovieController(MovieServiceImpl movieServiceImpl) {
        this.movieServiceImpl = movieServiceImpl;
    }

    @GetMapping({"/", "index"})
    public String viewIndexPage(Model model) {
        model.addAttribute("moviesList", movieServiceImpl.movieList());
        return "index";
    }

    @GetMapping("/addMovieForm")
    public String addMovie(Model model, Movie movie) {
        model.addAttribute("addMovieForm", movie);
        return "new_movie_window";
    }

    @PostMapping("/saveMovie")
    public String saveMovie(Movie movie) {
        movieServiceImpl.saveMovie(movie);
        return "redirect:/";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showEditMovie(Model model, @PathVariable Long id) {
        if (movieServiceImpl.showUpdateForm(id) == null) {
            return "error_404";
        } else {
            model.addAttribute("showUpdateForm", movieServiceImpl.showUpdateForm(id));
            return "edit_movie";
        }
    }

    @PostMapping("/updateMovie/{id}")
    public String updateMovie(Movie movie, @PathVariable Long id) {
        if (movieServiceImpl.showUpdateForm(id) == null) {
            return "error_404";
        } else {
            movieServiceImpl.saveMovie(movie);
        }
        return "redirect:/";
    }

    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable Long id) {
        if (movieServiceImpl.movieExists(id)) {
            movieServiceImpl.deleteMovieById(id);
            return "redirect:/";
        } else {
            return "error_404";
        }
    }
}