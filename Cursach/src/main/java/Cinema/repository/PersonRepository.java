package Cinema.repository;

import Cinema.entity.Person;
import Cinema.entity.enums.GenderEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PersonRepository<T extends Person, ID> extends JpaRepository<T, ID> {

    List<T> findByName(String name);

    List<T> findBySurname(String surname);

    List<T> findByDateOfBirth(Date dateOfBirth);

    List<T> findByGender(GenderEnum gender);
}
