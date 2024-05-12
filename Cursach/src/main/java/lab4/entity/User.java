package lab4.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString()

@Entity
@Table(name = "users")
public class User extends Person {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
