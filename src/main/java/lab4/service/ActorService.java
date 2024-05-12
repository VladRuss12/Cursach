package lab4.service;

import lab4.entity.Actor;
import lab4.entity.Movie;
import lab4.entity.Person;
import lab4.entity.enums.GenderEnum;

import java.util.Date;
import java.util.List;

public interface ActorService extends PersonService<Actor> {

    List<Actor> findByMovieTitle(String title);
}
