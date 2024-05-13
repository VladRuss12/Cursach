package Cinema.service;

import Cinema.entity.Actor;

import java.util.List;

public interface ActorService extends PersonService<Actor> {

    List<Actor> findByMovieTitle(String title);
}
