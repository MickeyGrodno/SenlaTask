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
import org.hibernate.annotations.CascadeType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "customer")
public class Customer implements eu.senla.shabalin.entity.Entity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "age", nullable = false)
    private long age;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<Orders> ordersList;



    public Customer(String firstName, String lastName, long age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
