package lab4.service;

import lab4.entity.AbstractEntity;
import lab4.entity.enums.GenderEnum;

import java.util.Date;
import java.util.List;

public interface PersonService<T extends AbstractEntity> extends AbstractService<T> {
    List<T> findByName(String name);
    List<T> findBySurname(String surname);
    List<T> findByDateOfBirth(Date dateOfBirth);
    List<T> findByGender(GenderEnum gender);
}
