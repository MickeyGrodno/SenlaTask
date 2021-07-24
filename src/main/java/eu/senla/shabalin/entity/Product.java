package eu.senla.shabalin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.persistence.Entity;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements eu.senla.shabalin.entity.Entity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "cost", nullable = false)
    private long cost;

    @ManyToMany(mappedBy = "productList")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Orders> ordersList;

    public Product(String productName, long cost) {
        this.productName = productName;
        this.cost = cost;
    }
}
