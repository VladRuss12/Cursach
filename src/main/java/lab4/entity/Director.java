package lab4.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString()

@Entity
@Table(name = "directors")
public class Director extends Person {
	@Column(name = "biography")
	private String biography;

	@OneToMany(mappedBy = "director")
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Movie> movies;
}