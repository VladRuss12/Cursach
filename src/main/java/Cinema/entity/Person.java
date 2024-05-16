package Cinema.entity;

import Cinema.entity.enums.GenderEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data

@Entity
public abstract class Person extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;

    @Transient
    private String firstname;

    @Column(name = "surname")
    private String surname;

    public void setName(String name) {
        this.name = name;
        // Разделение имени на firstname и surname
        String[] parts = name.split(" ");
        if (parts.length > 1) {
            this.firstname = parts[0];
            this.surname = parts[1];
        } else {
            this.firstname = name;
            this.surname = "";
        }
    }
}