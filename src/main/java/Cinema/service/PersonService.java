package Cinema.service;

import Cinema.entity.AbstractEntity;

import java.util.Date;
import java.util.List;

public interface PersonService<T extends AbstractEntity> extends AbstractService<T> {
    List<T> findByName(String name);

    List<T> findByDateOfBirth(Date dateOfBirth);

    List<T> findByGender(String gender);
}
