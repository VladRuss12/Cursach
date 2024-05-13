package Cinema.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString()

@Entity
@Table(name = "admins")
public class Admin extends User {

    @Column(name = "admin_level")
    private int adminLevel;
}
