package eu.senla.shabalin.dao;

import eu.senla.shabalin.dao.interfaces.OrderProductDao;

public class OrderProductDaoImpl extends AbstractDaoImpl implements OrderProductDao {
    public OrderProductDaoImpl() {
        super(OrderProduct.class);
    }
}
