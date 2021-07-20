package eu.senla.shabalin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private long age;
}
