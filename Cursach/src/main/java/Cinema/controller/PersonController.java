//package Cinema.controller;
//
//import Cinema.entity.Person;
//import Cinema.service.PersonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//import java.util.List;
//
//@RestController
//@RequestMapping("/persons")
//public abstract class PersonController<T extends Person> extends AbstractController<T> {
//
//    private final PersonService<T> personService;
//
//    @Autowired
//    public PersonController(@Qualifier("personServiceImpl") PersonService<T> personService) {
//        this.personService = personService;
//    }
//
//    @Override
//    public PersonService<T> getService() {
//        return this.personService;
//    }
//
//    @GetMapping("/name/{name}")
//    public ResponseEntity<List<T>> getPersonsByName(@PathVariable String name) {
//        List<T> persons = personService.findByName(name);
//        if (persons.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(persons, HttpStatus.OK);
//    }
//
//
//
//    @GetMapping("/dob/{dateOfBirth}")
//    public ResponseEntity<List<T>> getPersonsByDateOfBirth(@PathVariable Date dateOfBirth) {
//        List<T> persons = personService.findByDateOfBirth(dateOfBirth);
//        if (persons.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(persons, HttpStatus.OK);
//    }
//
//    @GetMapping("/gender/{gender}")
//    public ResponseEntity<List<T>> getPersonsByGender(@PathVariable String gender) {
//        List<T> persons = personService.findByGender(gender);
//        if (persons.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(persons, HttpStatus.OK);
//    }
//}