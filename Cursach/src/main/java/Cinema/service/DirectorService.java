package Cinema.service;

import Cinema.entity.Director;
import Cinema.entity.Movie;

import java.util.List;


public interface DirectorService extends PersonService<Director> {
    List<Director> findByMovie(Movie movie);
    List<Director> findByName(String name);
}
