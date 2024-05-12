package lab4.repository;


import lab4.entity.Review;

import lab4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends PersonRepository<User, Long>{
    User findByEmail(String email);
}

