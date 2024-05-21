package Cinema.service;

import Cinema.entity.Director;
import Cinema.entity.Movie;

import java.util.List;

public interface MovieService extends AbstractService<Movie> {

    List<Movie> findByTitle(String title);

    List<Movie> findByGenre(String genre);

    List<Movie> findByReleaseYear(int year);

    List<Movie> findByAvgRatingGreaterThan(int rating);

    List<Movie> findByDirector(Director director);

    //double calculateAverageRating(Long movieId);
}

