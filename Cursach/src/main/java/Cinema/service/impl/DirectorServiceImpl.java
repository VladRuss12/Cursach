package Cinema.service.impl;

import Cinema.entity.Director;
import Cinema.entity.Movie;
import Cinema.repository.DirectorRepository;
import Cinema.repository.MovieRepository;
import Cinema.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl extends PersonServiceImpl<Director> implements DirectorService {

    private DirectorRepository directorRepository;
    private MovieRepository movieRepository;

    @Autowired
    public DirectorServiceImpl(DirectorRepository directorRepository, MovieRepository movieRepository) {
        super(directorRepository);
        this.directorRepository = directorRepository;
        this.movieRepository = movieRepository;
    }
    @Override
    public void save(Director director) {
        List<Movie> movies = director.getMovies();
        for (Movie movie : movies) {
            if (!movie.getDirector().equals(director)) {
                movie.setDirector(director);
            }
            movieRepository.save(movie);
        }
        directorRepository.save(director);
    }

    @Override
    public void delete(Long id) {
        // Проверяем, существует ли режиссер с данным ID
        Optional<Director> directorOptional = directorRepository.findById(id);
        if (directorOptional.isPresent()) {
            Director director = directorOptional.get();
            List<Movie> movies = director.getMovies();
            // Удаляем режиссера из каждого фильма
            for (Movie movie : movies) {
                if (movie.getDirector().equals(director)) {
                    movie.setDirector(null);
                }
                movieRepository.save(movie);
            }
            // Удаляем режиссера из репозитория
            directorRepository.deleteById(id);
        } else {
            // Если режиссера с таким ID не существует, можно выбросить исключение или обработать эту ситуацию
            throw new EntityNotFoundException("Director with id " + id + " not found");
        }
    }

    @Override
    public void edit(Director director) {
        Director existingDirector = directorRepository.findById(director.getId())
                .orElseThrow(() -> new EntityNotFoundException("Director with id " + director.getId() + " not found"));
        existingDirector.setName(director.getName());
        existingDirector.setDateOfBirth(director.getDateOfBirth());
        existingDirector.setGender(director.getGender());
        existingDirector.setBiography(director.getBiography());
        // Обновление специфичных для Director полей
        directorRepository.save(existingDirector);

        // Вызов метода edit из суперкласса для обновления общих полей Person
        super.edit(director);
    }

    @Override
    public List<Director> findByMovieTitle(String title) {
        return directorRepository.findByMovieTitle(title);
    }


    // Дополнительные методы, если необходимо
}