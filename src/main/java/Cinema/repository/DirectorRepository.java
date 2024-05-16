package Cinema.repository;

import Cinema.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends PersonRepository<Director, Long> {

    @Query("SELECT d FROM Director d JOIN d.movies m WHERE m.title = :movieTitle")
    List<Director> findByMovieTitle(@Param("movieTitle") String movieTitle);
}