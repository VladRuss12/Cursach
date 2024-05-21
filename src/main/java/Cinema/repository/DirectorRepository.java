package Cinema.repository;

import Cinema.entity.Director;
import Cinema.entity.Movie;
import Cinema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends PersonRepository<Director, Long> {

}