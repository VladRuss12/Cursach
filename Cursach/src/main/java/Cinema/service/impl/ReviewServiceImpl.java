package Cinema.service.impl;

import Cinema.entity.Movie;
import Cinema.entity.Review;
import Cinema.entity.User;
import Cinema.exeption.ResourceNotFoundException;
import Cinema.repository.MovieRepository;
import Cinema.repository.ReviewRepository;
import Cinema.repository.UserRepository;
import Cinema.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }



    @Override
    public Review read(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review with id " + id + " not found"));
    }

    @Override
    public List<Review> read() {
        return reviewRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Review review) {
        // Получите текущего пользователя
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (review.getMovie() == null) {
            throw new IllegalArgumentException("Review must be associated with a movie");
        }

        // Сохраняем или обновляем фильм
        Movie movie = movieRepository.findById(review.getMovie().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id " + review.getMovie().getId() + " not found"));

        // Устанавливаем связи отзыва с пользователем и фильмом
        review.setUser(currentUser);
        review.setMovie(movie);

        // Сохраняем отзыв
        reviewRepository.save(review);
    }



    @Override
    @Transactional
    public void delete(Long id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review with id " + id + " not found"));

        // Проверяем, является ли текущий пользователь автором отзыва
        if (!review.getUser().equals(currentUser)) {
            throw new IllegalArgumentException("You can only delete your own reviews");
        }

        User user = review.getUser();
        Movie movie = review.getMovie();

        // Удаляем связи отзыва с пользователем и фильмом
        user.getReviews().remove(review);
        movie.getReviews().remove(review);

        userRepository.save(user);
        movieRepository.save(movie);

        // Удаляем отзыв
        reviewRepository.delete(review);
    }

    @Override
    @Transactional
    public void edit(Review updatedReview) {
        Review review = reviewRepository.findById(updatedReview.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Review with id " + updatedReview.getId() + " not found"));

        // Обновляем информацию об отзыве
        review.setRating(updatedReview.getRating());
        review.setComment(updatedReview.getComment());

        // Обновляем связи отзыва с пользователем и фильмом
        User user = userRepository.findById(updatedReview.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + updatedReview.getUser().getId() + " not found"));
        Movie movie = movieRepository.findById(updatedReview.getMovie().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id " + updatedReview.getMovie().getId() + " not found"));

        if (user != null) {
            user.getReviews().add(review);
            userRepository.save(user);
        }

        if (movie != null) {
            movie.getReviews().add(review);
            movieRepository.save(movie);
        }

        review.setUser(user);
        review.setMovie(movie);

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
