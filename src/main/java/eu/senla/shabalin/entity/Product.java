package eu.senla.shabalin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product implements Entity{
    private long id;
    private String productName;
    private long cost;

    public Product(String productName, long cost) {
        this.productName = productName;
        this.cost = cost;
    }
}
