package Cinema.controller;

import Cinema.entity.Review;
import Cinema.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController extends AbstractController<Review> {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public ReviewService getService() {
        return reviewService;
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Review>> getByRating(@PathVariable int rating) {
        List<Review> reviews = reviewService.findByRating(rating);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviews, headers, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getByUserId(@PathVariable Long userId) {
        List<Review> reviews = reviewService.findByUserId(userId);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviews, headers, HttpStatus.OK);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Review>> getByMovieId(@PathVariable Long movieId) {
        List<Review> reviews = reviewService.findByMovieId(movieId);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviews, headers, HttpStatus.OK);
    }

}
