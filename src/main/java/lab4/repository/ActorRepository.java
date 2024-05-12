package lab4.repository;

        import lab4.entity.Actor;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface ActorRepository extends PersonRepository<Actor, Long> {
    List<Actor> findByMovieTitle(String title);
}