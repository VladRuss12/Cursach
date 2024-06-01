package Cinema.service.impl;

import Cinema.entity.Director;
import Cinema.entity.Movie;
import Cinema.exeption.ResourceNotFoundException;
import Cinema.repository.DirectorRepository;
import Cinema.repository.MovieRepository;
import Cinema.service.DirectorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public DirectorServiceImpl(DirectorRepository directorRepository, MovieRepository movieRepository) {
        this.directorRepository = directorRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Director read(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director with id " + id + " not found"));
    }

    @Override
    public List<Director> read() {
        return directorRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Director director) {
        // Сохраняем режиссера
        Director savedDirector = directorRepository.save(director);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director with id " + id + " not found"));

        // Удаляем связи с фильмами
        for (Movie movie : director.getMovies()) {
            movie.setDirector(null);
        }
        movieRepository.saveAll(director.getMovies());

        // Удаляем фильмы
        movieRepository.deleteAll(director.getMovies());

        // Удаляем режиссера
        directorRepository.delete(director);
    }

    @Override
    @Transactional
    public void edit(Director updatedDirector) {
        Director director = directorRepository.findById(updatedDirector.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Director with id " + updatedDirector.getId() + " not found"));

        // Обновляем информацию о режиссере
        director.setName(updatedDirector.getName());
        director.setDateOfBirth(updatedDirector.getDateOfBirth());
        director.setGender(updatedDirector.getGender());
        director.setBiography(updatedDirector.getBiography());
        director.setInterestingFact(updatedDirector.getInterestingFact());

        // Обновляем связи с фильмами
        for (Movie movie : director.getMovies()) {
            movie.setDirector(director);
        }
        movieRepository.saveAll(director.getMovies());

        // Сохраняем обновленного режиссера
        directorRepository.save(director);
    }

    @Override
    public List<Director> findByName(String name) {
        return directorRepository.findByName(name);
    }

    @Override
    public List<Director> findByDateOfBirth(Date dateOfBirth) {
        return directorRepository.findByDateOfBirth(dateOfBirth);
    }

    @Override
    public List<Director> findByGender(String gender) {
        return directorRepository.findByGender(gender);
    }

    @Override
    public List<Director> findByMovie(Movie movie) {
        return directorRepository.findByMovies(movie);
    }
}
