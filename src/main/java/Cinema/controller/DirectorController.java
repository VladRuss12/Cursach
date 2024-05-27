package Cinema.controller;

import Cinema.entity.Director;
import Cinema.entity.Movie;
import Cinema.service.DirectorService;
import Cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorController extends AbstractController<Director> {

    private final DirectorService directorService;
    private final MovieService movieService;

    @Autowired
    public DirectorController(@Qualifier("directorServiceImpl") DirectorService directorService, MovieService movieService) {
        this.directorService = directorService;
        this.movieService = movieService;
    }

    @Override
    public DirectorService getService() {
        return this.directorService;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Director>> getDirectorsByName(@PathVariable String name) {
        List<Director> directors = directorService.findByName(name);
        if (directors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Director>> getDirectorsByMovie(@PathVariable Long movieId) {
        Movie movie = movieService.read(movieId);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Director> directors = directorService.findByMovie(movie);
        if (directors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }

    @GetMapping("/dateOfBirth/{dateOfBirth}")
    public ResponseEntity<List<Director>> getDirectorsByDateOfBirth(@PathVariable Date dateOfBirth) {
        List<Director> directors = directorService.findByDateOfBirth(dateOfBirth);
        if (directors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Director>> getDirectorsByGender(@PathVariable String gender) {
        List<Director> directors = directorService.findByGender(gender);
        if (directors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(directors, HttpStatus.OK);
    }
}