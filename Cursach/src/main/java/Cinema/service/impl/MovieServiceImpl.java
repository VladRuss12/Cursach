package Cinema.service.impl;

import Cinema.entity.*;
import Cinema.exeption.ResourceNotFoundException;
import Cinema.repository.DirectorRepository;
import Cinema.repository.MovieRepository;
import Cinema.repository.ReviewRepository;
import Cinema.service.MovieService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final DirectorRepository directorRepository;


    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, DirectorRepository directorRepository,ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Movie read(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id " + id + " not found"));
    }

    @Override
    public Movie findRandomMovie() {
        List<Movie> allMovies = movieRepository.findAll();
        if (allMovies.isEmpty()) {
            throw new ResourceNotFoundException("No movies found");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(allMovies.size());
        return allMovies.get(randomIndex);
    }

    @Override
    public List<Movie> read() {
        return movieRepository.findAll();
    }
    @Override
    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public List<Movie> findByReleaseYear(int year) {
        return movieRepository.findByReleaseYear(year);
    }

    @Override
    public List<Movie> findByAvgRatingGreaterThan(double rating) {
        // Получаем все фильмы
        List<Movie> allMovies = movieRepository.findAll();

        // Фильтруем фильмы, у которых средний рейтинг больше указанного значения
        List<Movie> filteredMovies = allMovies.stream()
                .filter(movie -> calculateAverageRating(movie) >= rating)
                .collect(Collectors.toList());

        return filteredMovies;
    }

    @Override
    public List<Movie> findByDirector(Director director) {
        return movieRepository.findByDirector(director);
    }

    @Transactional
    public void save(Movie movie) {
        // Находим существующего режиссера по id
        Director director = null;
        if (movie.getDirector() != null) {
            director = directorRepository.findById(movie.getDirector().getId()).orElse(null);
        }
        if (director != null) {
            // Устанавливаем режиссера в фильме
            movie.setDirector(director);
        }

        // Сохраняем фильм
        Movie savedMovie = movieRepository.save(movie);
        movieRepository.save(savedMovie);

        // Обновляем связь с режиссером
        if (director != null) {
            director.getMovies().add(savedMovie);
            directorRepository.save(director);
        }
    }



    @Override
    @Transactional
    public void delete(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id " + id + " not found"));

        // Удаляем связи с отзывами
        for (Review review : movie.getReviews()) {
            review.setMovie(null);
        }
        reviewRepository.saveAll(movie.getReviews());

        // Удаляем связь с режиссером
        Director director = movie.getDirector();
        if (director != null) {
            director.getMovies().remove(movie);
            directorRepository.save(director);
        }

        // Удаляем фильм
        movieRepository.delete(movie);
    }

    @Override
    @Transactional
    public void edit(Movie updatedMovie) {
        Movie movie = movieRepository.findById(updatedMovie.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id " + updatedMovie.getId() + " not found"));

        // Обновляем информацию о фильме
        movie.setTitle(updatedMovie.getTitle());
        movie.setDescription(updatedMovie.getDescription());
        movie.setReleaseYear(updatedMovie.getReleaseYear());
        movie.setGenre(updatedMovie.getGenre());

        // Обновляем связь с режиссером, если она изменилась
        Director newDirector = directorRepository.findById(updatedMovie.getDirector().getId()).orElse(null);
        Director oldDirector = movie.getDirector();
        if (newDirector != null && !newDirector.equals(oldDirector)) {
            if (oldDirector != null) {
                oldDirector.getMovies().remove(movie);
                directorRepository.save(oldDirector);
            }
            movie.setDirector(newDirector);
            newDirector.getMovies().add(movie);
            directorRepository.save(newDirector);
        }

        // Обновляем связи с отзывами
        for (Review review : movie.getReviews()) {
            review.setMovie(movie);
        }
        reviewRepository.saveAll(movie.getReviews());

        // Сохраняем обновленный фильм
        movieRepository.save(movie);
    }


    public double calculateAverageRating(Movie movie) {
        return movie.getReviews().stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
}
