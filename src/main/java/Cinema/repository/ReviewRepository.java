package Cinema.repository;


import Cinema.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRating(int rating);

    List<Review> findByUserId(Long userId);

    List<Review> findByMovieId(Long movieId);
}

