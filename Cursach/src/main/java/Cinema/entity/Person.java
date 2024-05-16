package Cinema.entity;

import Cinema.entity.enums.GenderEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public abstract class Person extends AbstractEntity {


    @Column(name = "name")
    private String name;

    @Transient
    private String firstName;

    @Transient
    private String lastName;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;


    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;

    public void setName(String name) {
        this.name = name;
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts.length > 1 ? parts[1] : "";
    }

    public String getfirstName() {
        return this.firstName;
    }

    public String getlastName() {
        return this.lastName;
    }
}
