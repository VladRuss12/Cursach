package Cinema.repository;

        import Cinema.entity.Actor;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface ActorRepository extends PersonRepository<Actor, Long> {
    List<Actor> findByMovieTitle(String title);
}