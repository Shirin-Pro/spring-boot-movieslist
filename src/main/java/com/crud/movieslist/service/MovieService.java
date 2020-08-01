package com.crud.movieslist.service;

import com.crud.movieslist.domain.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> movieList();

    void saveMovie(Movie entity);

    Movie showUpdateForm(Long id);

    void deleteMovieById(Long id);

    boolean movieExists(Long id);

}
