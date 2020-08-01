package com.crud.movieslist.service;

import com.crud.movieslist.domain.Movie;
import com.crud.movieslist.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> movieList() {
        return (List<Movie>) movieRepository.findAll();
    }

    public void saveMovie(Movie entity) {
        movieRepository.save(entity);
    }

    public Movie showUpdateForm(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public void deleteMovieById(Long id) {
        if (movieExists(id)) {
            movieRepository.deleteById(id);
        }
    }

    public boolean movieExists(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.isPresent();
    }
}
