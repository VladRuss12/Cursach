package lab4.service.impl;

import lab4.entity.Review;
import lab4.repository.MovieRepository;
import lab4.repository.ReviewRepository;
import lab4.repository.UserRepository;
import lab4.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ReviewServiceImpl extends AbstractServiceImpl<Review> implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, MovieRepository movieRepository) {
        super(reviewRepository);
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }
    @Override
    public void delete(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        // Удаляем отзыв
        reviewRepository.delete(review);
        // Проверяем, есть ли другие отзывы у пользователя и фильма перед удалением
        if (userRepository.findById(review.getUser().getId()).isPresent()) {
            userRepository.delete(review.getUser());
        }
        if (movieRepository.findById(review.getMovie().getId()).isPresent()) {
            movieRepository.delete(review.getMovie());
        }
    }

    @Override
    public void save(Review review) {
        if (review.getUser() == null || review.getMovie() == null) {
            throw new IllegalArgumentException("Review must be associated with a user and a movie");
        }
        // Сохраняем или обновляем пользователя и фильм
        userRepository.save(review.getUser());
        movieRepository.save(review.getMovie());
        // Сохраняем отзыв
        reviewRepository.save(review);
    }

    @Override
    public void edit(Review updatedReview) {
        Review review = reviewRepository.findById(updatedReview.getId())
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        review.setRating(updatedReview.getRating());
        review.setComment(updatedReview.getComment());
        // Обновляем отзыв с новым пользователем и фильмом
        review.setUser(updatedReview.getUser());
        review.setMovie(updatedReview.getMovie());
        // Сохраняем обновленную информацию пользователя и фильма
        userRepository.save(review.getUser());
        movieRepository.save(review.getMovie());
        // Сохраняем обновленный отзыв
        reviewRepository.save(review);
    }




    @Override
    public List<Review> findByRating(int rating) {
        return reviewRepository.findByRating(rating);
    }

    @Override
    public List<Review> findByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    @Override
    public List<Review> findByMovieId(Long movieId) {
        return reviewRepository.findByMovieId(movieId);
    }
}
