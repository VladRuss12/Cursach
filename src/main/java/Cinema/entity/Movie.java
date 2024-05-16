package Cinema.entity;

import javax.persistence.*;

import Cinema.entity.enums.GenreEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true, of = {"title"})
@ToString(callSuper = true)

@Entity
@Table(name = "movies")
public class Movie extends AbstractEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private int releaseYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private GenreEnum genre;

    @Column(name = "avgRating")
    private int avgRating;

    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @ManyToMany
    @JoinTable(name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;

    @OneToMany(mappedBy = "movie", orphanRemoval = true)
    private List<Review> reviews;
}