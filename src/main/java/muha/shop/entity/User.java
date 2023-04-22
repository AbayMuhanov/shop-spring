package muha.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private UserRole role;

    private String Login;

    private String Password;

//    private String name;
//
//    private String lastNane;
//
//    private Integer dateOfCreation;


}
