package lab4.service;

import lab4.entity.Director;
import lab4.entity.Movie;
import lab4.entity.enums.GenreEnum;

import java.util.List;

public interface MovieService extends AbstractService<Movie> {

    List<Movie>findByTitle(String title);

    List<Movie> findByGenre(GenreEnum genre);

    List<Movie> findByReleaseYear(int year);

    List<Movie> findByAvgRatingGreaterThan(int rating);

    List<Movie> findByDirector(Director director);
}

