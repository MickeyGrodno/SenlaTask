package eu.senla.shabalin.utils;

import eu.senla.shabalin.entity.Customer;
import eu.senla.shabalin.entity.Orders;
import eu.senla.shabalin.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static Session session;

    private HibernateSessionFactoryUtil() {
    }

    public static Session getSession() {
        if(session == null) {
            session = getSessionFactory().openSession();
        } else {
            session = getSessionFactory().getCurrentSession();
        }
            return session;

    }

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Orders.class);
            configuration.addAnnotatedClass(Product.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }
}
