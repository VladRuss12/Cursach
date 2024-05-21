package Cinema.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true, of = {"email"})
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends Person {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    //@Column(name = "user_role")
    private String role;

    @JsonManagedReference(value = "user-review")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Review> reviews;
}