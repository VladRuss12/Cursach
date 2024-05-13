package Cinema.repository;

import Cinema.entity.Director;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends PersonRepository<Director, Long> {
    List<Director> findByMovieTitle(String title);
}
