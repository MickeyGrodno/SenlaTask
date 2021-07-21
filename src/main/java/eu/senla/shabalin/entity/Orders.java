package eu.senla.shabalin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Entity{
    private long id;
    private long customerId;
    private LocalDate orderDate;

    public Orders(long customerId, LocalDate order_date) {
        this.customerId = customerId;
        this.orderDate = order_date;
    }
}
