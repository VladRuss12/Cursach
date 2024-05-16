package lab4.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString()

@Entity
@Table(name = "actors")
public class Actor extends Person {

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}

