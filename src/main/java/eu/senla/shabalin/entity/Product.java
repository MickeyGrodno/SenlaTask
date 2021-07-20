package eu.senla.shabalin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Product {
    private long id;
    private String productName;
    private long cost;
}
