package eu.senla.shabalin.dao;

import eu.senla.shabalin.dao.interfaces.AbstractDao;
import eu.senla.shabalin.dao.interfaces.ProductDao;
import eu.senla.shabalin.entity.Product;

public class ProductDaoImpl extends AbstractDaoImpl implements ProductDao {
    public ProductDaoImpl() {
        super(Product.class);
    }
}
