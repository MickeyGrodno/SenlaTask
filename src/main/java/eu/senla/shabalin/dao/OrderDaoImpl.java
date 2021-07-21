package eu.senla.shabalin.dao;

import eu.senla.shabalin.dao.interfaces.AbstractDao;
import eu.senla.shabalin.entity.Order;

public class OrderDaoImpl extends AbstractDaoImpl implements AbstractDao {
    public OrderDaoImpl() {
        super(Order.class);
    }
}
