package Cinema.service.impl;

import Cinema.entity.Actor;
import Cinema.entity.Movie;
import Cinema.repository.ActorRepository;
import Cinema.repository.MovieRepository;
import Cinema.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl extends PersonServiceImpl<Actor> implements ActorService {

    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository, MovieRepository movieRepository) {
        super(actorRepository);
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void save(Actor entity) {
        List<Movie> movies = entity.getMovies();
        for (Movie movie : movies) {
            if (!movie.getActors().contains(entity)) {
                movie.getActors().add(entity);
            }
            movieRepository.save(movie);
        }
        actorRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        // Проверяем, существует ли актер с данным ID
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (actorOptional.isPresent()) {
            Actor actor = actorOptional.get();
            List<Movie> movies = actor.getMovies();
            // Удаляем актера из списка актеров каждого фильма
            for (Movie movie : movies) {
                movie.getActors().remove(actor);
                movieRepository.save(movie);
            }
            // Удаляем актера из репозитория
            actorRepository.deleteById(id);
        } else {
            // Если актера с таким ID не существует, можно выбросить исключение или обработать эту ситуацию
            throw new EntityNotFoundException("Actor with id " + id + " not found");
        }
    }


    @Override
    public List<Actor> findByMovieTitle(String title) {
        return actorRepository.findByMovieTitle(title);
    }

}
