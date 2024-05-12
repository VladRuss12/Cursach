package lab4.controller;

import lab4.entity.Person;
import lab4.entity.enums.GenderEnum;
import lab4.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController<T extends Person> extends AbstractController<T> {

    @Autowired
    private PersonService<T> personService;

    @Override
    public PersonService<T> getService() {
        return this.personService;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<T>> getPersonsByName(@PathVariable String name) {
        List<T> persons = personService.findByName(name);
        if (persons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/surname/{surname}")
    public ResponseEntity<List<T>> getPersonsBySurname(@PathVariable String surname) {
        List<T> persons = personService.findBySurname(surname);
        if (persons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/dob/{dateOfBirth}")
    public ResponseEntity<List<T>> getPersonsByDateOfBirth(@PathVariable Date dateOfBirth) {
        List<T> persons = personService.findByDateOfBirth(dateOfBirth);
        if (persons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<T>> getPersonsByGender(@PathVariable GenderEnum gender) {
        List<T> persons = personService.findByGender(gender);
        if (persons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }
}
