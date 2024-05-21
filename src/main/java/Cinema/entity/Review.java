package Cinema.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true, of = {"comment", "rating"})
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review extends AbstractEntity {

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JsonBackReference(value = "user-review")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonBackReference(value = "movie-review")
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
