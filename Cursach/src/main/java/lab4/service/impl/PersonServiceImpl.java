package lab4.service.impl;

import lab4.entity.Person;
import lab4.entity.User;
import lab4.entity.enums.GenderEnum;
import lab4.repository.PersonRepository;
import lab4.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public abstract class PersonServiceImpl<T extends Person> extends AbstractServiceImpl<T> implements PersonService<T> {

    private PersonRepository<T, Long> personRepository;

    @Override
    public void edit(T person) {
        T existingPerson = personRepository.findById(person.getId())
                .orElseThrow(() -> new EntityNotFoundException("Person with id " + person.getId() + " not found"));

        existingPerson.setName(person.getName()); // Обновление имени и автоматическое разделение на firstName и lastName
        existingPerson.setDateOfBirth(person.getDateOfBirth()); // Обновление даты рождения
        existingPerson.setGender(person.getGender()); // Обновление пола

        personRepository.save(existingPerson);
    }


    @Autowired
    public PersonServiceImpl(PersonRepository<T, Long> personRepository) {
        super(personRepository);
        this.personRepository = personRepository;
    }
    @Override
    public List<T> findByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public List<T> findBySurname(String surname) {
        return personRepository.findBySurname(surname);
    }

    @Override
    public List<T> findByDateOfBirth(Date dateOfBirth) {
        return personRepository.findByDateOfBirth(dateOfBirth);
    }

    @Override
    public List<T> findByGender(GenderEnum gender) {
        return personRepository.findByGender(gender);
    }
}
