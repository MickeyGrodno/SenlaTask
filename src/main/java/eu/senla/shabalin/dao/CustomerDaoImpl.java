package eu.senla.shabalin.dao;

import eu.senla.shabalin.dao.interfaces.AbstractDao;
import eu.senla.shabalin.dao.interfaces.CustomerDao;
import eu.senla.shabalin.entity.Customer;

public class CustomerDaoImpl extends AbstractDaoImpl implements CustomerDao {
    public CustomerDaoImpl() {
        super(Customer.class);
    }
}
