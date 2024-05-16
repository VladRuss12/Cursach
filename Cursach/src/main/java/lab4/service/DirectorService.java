package lab4.service;

import lab4.entity.Director;
import lab4.entity.Movie;
import lab4.entity.Person;

import java.util.List;


public interface DirectorService extends PersonService<Director> {
    List<Director> findByMovieTitle(String title);
}
