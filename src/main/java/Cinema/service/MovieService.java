package Cinema.service;

import Cinema.entity.Director;
import Cinema.entity.Movie;
import Cinema.entity.enums.GenreEnum;

import java.util.List;

public interface MovieService extends AbstractService<Movie> {

    List<Movie>findByTitle(String title);

    List<Movie> findByGenre(GenreEnum genre);

    List<Movie> findByReleaseYear(int year);

    List<Movie> findByAvgRatingGreaterThan(int rating);

    List<Movie> findByDirector(Director director);

    double calculateAverageRating(Long movieId);
}

