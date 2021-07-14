package eu.senla.shabalin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Computer {
    private String name;
    private Date introduced;
    private Date discontinued;
    private String company;
}
