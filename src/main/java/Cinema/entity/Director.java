package Cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true, of = {"biography"})
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "directors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Director extends Person {

    @Column(name = "biography")
    private String biography;

    
    @Column(name = "interesting_fact")
    private String interestingFact;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "director", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Movie> movies;
}
