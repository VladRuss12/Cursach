package lab4.service;

import lab4.entity.Review;

import java.util.List;

public interface ReviewService extends AbstractService<Review> {
    List<Review> findByRating(int rating);

    List<Review> findByUserId(Long userId);

    List<Review> findByMovieId(Long movieId);
}
