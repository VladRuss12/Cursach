package Cinema.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true, of = {"title"})
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "movies")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movie extends AbstractEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "genre")
    private String genre;

    //@Column(name = "avgRating")
    private Double avgRating;

    @ManyToOne
    @JoinColumn(name = "`director_id`", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Director director;

    @JsonManagedReference(value = "movie-review")
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Review> reviews;
}
