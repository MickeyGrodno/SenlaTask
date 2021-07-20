package eu.senla.shabalin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class Order {
    private long id;
    private long userId;
    private Date date;
}
