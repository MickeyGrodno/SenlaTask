package eu.senla.shabalin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String eMail;
    private String firstName;
    private String lastName;
    private String avatar;
}
