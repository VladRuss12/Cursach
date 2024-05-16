package Cinema.repository;

import Cinema.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends PersonRepository<Actor, Long> {

    @Query("SELECT a FROM Actor a JOIN a.movies m WHERE m.title = :movieTitle")
    List<Actor> findByMovieTitle(@Param("movieTitle") String movieTitle);
}