package eu.senla.shabalin.dao;

import eu.senla.shabalin.dao.interfaces.AbstractDao;
import eu.senla.shabalin.dao.interfaces.OrdersDao;
import eu.senla.shabalin.entity.Orders;

public class OrdersDaoImpl extends AbstractDaoImpl implements OrdersDao {
    public OrdersDaoImpl() {
        super(Orders.class);
    }
}
