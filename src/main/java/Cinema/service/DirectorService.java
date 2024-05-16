package Cinema.service;

import Cinema.entity.Director;

import java.util.List;


public interface DirectorService extends PersonService<Director> {
    List<Director> findByMovieTitle(String title);
}
