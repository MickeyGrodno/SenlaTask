package eu.senla.shabalin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CascadeType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders implements eu.senla.shabalin.entity.Entity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Temporal(TemporalType.DATE)
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Product> productList;

    public Orders(Customer customer, Date orderDate, List<Product> productList) {
        this.customer = customer;
        this.orderDate = orderDate;
        this.productList = productList;
    }
}
