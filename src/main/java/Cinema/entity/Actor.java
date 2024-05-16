package Cinema.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true, of = {"id"})
@ToString(callSuper = true)

@Entity
@Table(name = "actors")

public class Actor extends Person {

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}