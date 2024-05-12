package lab4.entity;

import lab4.entity.enums.GenderEnum;
import lab4.entity.enums.GenreEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@ToString()
@EqualsAndHashCode(callSuper = true)
@Entity
public abstract class Person extends AbstractEntity {

    @Getter
    @Column(name = "name")
    private String name;

    private String firstName;

    private String lastName;

    @Getter
    @Setter
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;

    public void setName(String name) {
        this.name = name;
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts.length > 1 ? parts[1] : "";
    }
}
