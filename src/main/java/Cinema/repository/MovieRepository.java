package Cinema.repository;

import Cinema.entity.Director;
import Cinema.entity.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);

    List<Movie> findByGenre(String genre);

    List<Movie> findByReleaseYear(int year);

    List<Movie> findByDirector(Director director);
}