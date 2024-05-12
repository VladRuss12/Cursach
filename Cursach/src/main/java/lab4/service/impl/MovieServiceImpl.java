package lab4.service.impl;

import lab4.entity.Actor;
import lab4.entity.Director;
import lab4.entity.Movie;
import lab4.entity.enums.GenreEnum;
import lab4.repository.ActorRepository;
import lab4.repository.DirectorRepository;
import lab4.repository.MovieRepository;
import lab4.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MovieServiceImpl extends AbstractServiceImpl<Movie> implements MovieService {
    private  ActorRepository actorRepository;
    private MovieRepository movieRepository;
    private DirectorRepository directorRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, DirectorRepository directorRepository, ActorRepository actorRepository) {
        super(movieRepository);
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> findByGenre(GenreEnum genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public List<Movie> findByReleaseYear(int year) {
        return movieRepository.findByReleaseYear(year);
    }

    @Override
    public List<Movie> findByAvgRatingGreaterThan(int rating) {
        return movieRepository.findByAvgRatingGreaterThan(rating);
    }

    @Override
    public List<Movie> findByDirector(Director director) {
        return movieRepository.findByDirector(director);
    }

    @Override
    public void edit(Movie movie) {
        Movie existingMovie = movieRepository.findById(movie.getId())
                .orElseThrow(() -> new EntityNotFoundException("Movie with id " + movie.getId() + " not found"));

        // Обновляем информацию о фильме
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setReleaseYear(movie.getReleaseYear());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setAvgRating(movie.getAvgRating());

        // Обновляем информацию о режиссере, если она изменилась
        if (!existingMovie.getDirector().equals(movie.getDirector())) {
            Director oldDirector = existingMovie.getDirector();
            oldDirector.getMovies().remove(existingMovie);
            directorRepository.save(oldDirector);

            existingMovie.setDirector(movie.getDirector());
            movie.getDirector().getMovies().add(existingMovie);
        }

        // Обновляем информацию об актерах
        for (Actor actor : existingMovie.getActors()) {
            if (!movie.getActors().contains(actor)) {
                actor.getMovies().remove(existingMovie);
                actorRepository.save(actor);
            }
        }
        for (Actor actor : movie.getActors()) {
            if (!existingMovie.getActors().contains(actor)) {
                actor.getMovies().add(existingMovie);
                actorRepository.save(actor);
            }
        }
        existingMovie.setActors(movie.getActors());

        movieRepository.save(existingMovie);
    }



    // Дополнительные методы, если необходимо
}
