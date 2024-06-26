package Cinema.controller;

import Cinema.entity.Director;
import Cinema.entity.Movie;
import Cinema.service.DirectorService;
import Cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController extends AbstractController<Movie> {

    private final MovieService movieService;
    private final DirectorService directorService;

    @Autowired
    public MovieController(MovieService movieService, DirectorService directorService) {
        this.movieService = movieService;
        this.directorService = directorService;
    }

    @Override
    public MovieService getService() {
        return movieService;
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Movie>> getMoviesByTitle(@PathVariable String title) {
        List<Movie> movies = movieService.findByTitle(title);
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movies, headers, HttpStatus.OK);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable String genre) {
        List<Movie> movies = movieService.findByGenre(genre);
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movies, headers, HttpStatus.OK);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Movie>> getMoviesByReleaseYear(@PathVariable int year) {
        List<Movie> movies = movieService.findByReleaseYear(year);
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movies, headers, HttpStatus.OK);
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Movie>> getMoviesByAvgRatingGreaterThan(@PathVariable int rating) {
        List<Movie> movies = movieService.findByAvgRatingGreaterThan(rating);
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movies, headers, HttpStatus.OK);
    }

    @GetMapping("/director/{directorId}")
    public ResponseEntity<List<Movie>> getMoviesByDirector(@PathVariable long directorId) {
        Director director = directorService.read(directorId);
        if (director == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Movie> movies = movieService.findByDirector(director);
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movies, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}/average-rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long id) {
        Movie movie = movieService.read(id);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        double averageRating = movieService.calculateAverageRating(movie);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<Movie> getRandomMovie() {
        Movie movie = movieService.findRandomMovie();
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie, headers, HttpStatus.OK);
    }


}

