package Cinema.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, of = {"comment", "rating"})
@ToString(callSuper = true)

@Entity
@Table(name = "reviews")
public class Review extends AbstractEntity {

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}