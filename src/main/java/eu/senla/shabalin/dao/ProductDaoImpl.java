package eu.senla.shabalin.dao;

import eu.senla.shabalin.dao.interfaces.AbstractDao;
import eu.senla.shabalin.dao.interfaces.OrdersDao;
import eu.senla.shabalin.dao.interfaces.ProductDao;
import eu.senla.shabalin.entity.Product;
import org.hibernate.Session;

public class ProductDaoImpl extends AbstractDaoImpl implements ProductDao {
    public static ProductDao productDao;
    public ProductDaoImpl(Session session) {
        super(session, Product.class);
    }

    public static ProductDao getProductDaoImpl(Session session) {
        if (productDao == null) {
            productDao = new ProductDaoImpl(session);
        }
        return productDao;
    }
}
