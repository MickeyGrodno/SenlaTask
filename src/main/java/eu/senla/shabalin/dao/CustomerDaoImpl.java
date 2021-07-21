package eu.senla.shabalin.dao;

import eu.senla.shabalin.dao.interfaces.AbstractDao;
import eu.senla.shabalin.entity.Customer;

public class CustomerDaoImpl extends AbstractDaoImpl implements AbstractDao {
    public CustomerDaoImpl() {
        super(Customer.class);
    }
}
