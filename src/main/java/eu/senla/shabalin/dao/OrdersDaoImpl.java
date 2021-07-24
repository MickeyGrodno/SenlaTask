package eu.senla.shabalin.dao;

import eu.senla.shabalin.dao.interfaces.AbstractDao;
import eu.senla.shabalin.dao.interfaces.CustomerDao;
import eu.senla.shabalin.dao.interfaces.OrdersDao;
import eu.senla.shabalin.entity.Orders;
import org.hibernate.Session;

public class OrdersDaoImpl extends AbstractDaoImpl implements OrdersDao {
    public static OrdersDao ordersDao;
    public OrdersDaoImpl(Session session) {
        super(session, Orders.class);
    }

    public static OrdersDao getOrdersDaoImpl(Session session) {
        if (ordersDao == null) {
            ordersDao = new OrdersDaoImpl(session);
        }
        return ordersDao;
    }
}
