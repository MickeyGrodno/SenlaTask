package eu.senla.shabalin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
}
