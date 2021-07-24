package eu.senla.shabalin.dao;

import eu.senla.shabalin.dao.interfaces.AbstractDao;
import eu.senla.shabalin.dao.interfaces.CustomerDao;
import eu.senla.shabalin.entity.Customer;
import org.hibernate.Session;

public class CustomerDaoImpl extends AbstractDaoImpl implements CustomerDao {
    public static CustomerDao customerDao;

    public CustomerDaoImpl(Session session) {
        super(session, Customer.class);
    }

    public static CustomerDao getCustomerDaoImpl(Session session) {
        if (customerDao == null) {
            customerDao = new CustomerDaoImpl(session);
        }
        return customerDao;
    }
}
