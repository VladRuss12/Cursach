package lab4.repository;

import lab4.entity.Director;
import lab4.entity.Movie;
import lab4.entity.enums.GenreEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);

    List<Movie> findByGenre(GenreEnum genre);

    List<Movie> findByReleaseYear(int year);

    List<Movie> findByAvgRatingGreaterThan(double rating);

    List<Movie> findByDirector(Director director);
}