package Cinema.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, of = {"email"})
@ToString(callSuper = true)

@Entity
@Table(name = "users")

public class User extends Person {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
