package Cinema.repository;

import Cinema.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface PersonRepository<T extends Person, ID extends Long> extends JpaRepository<T, ID> {
    List<T> findByDateOfBirth(Date dateOfBirth);
    List<T> findByGender(String gender);
}