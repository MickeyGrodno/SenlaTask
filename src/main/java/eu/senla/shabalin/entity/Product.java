package eu.senla.shabalin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Entity, Serializable {
    private long id;
    private String productName;
    private long cost;

    public Product(String productName, long cost) {
        this.productName = productName;
        this.cost = cost;
    }
}
