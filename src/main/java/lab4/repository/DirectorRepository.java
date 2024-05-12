package lab4.repository;

import lab4.entity.Actor;
import lab4.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends PersonRepository<Director, Long> {
    List<Director> findByMovieTitle(String title);
}
