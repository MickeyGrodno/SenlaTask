package eu.senla.shabalin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct implements Entity, Serializable {
    private Long id;
    private Long orderId;
    private Long productId;

    public OrderProduct(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }
}
