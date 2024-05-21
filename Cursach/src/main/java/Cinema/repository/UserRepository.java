package Cinema.repository;


import Cinema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PersonRepository<User, Long> {
    User findByEmail(String email);
}

