package eu.senla.shabalin;

import eu.senla.shabalin.dao.CustomerDaoImpl;
import eu.senla.shabalin.dao.OrdersDaoImpl;
import eu.senla.shabalin.dao.ProductDaoImpl;
import eu.senla.shabalin.dao.interfaces.CustomerDao;
import eu.senla.shabalin.dao.interfaces.OrdersDao;
import eu.senla.shabalin.dao.interfaces.ProductDao;
import eu.senla.shabalin.entity.Customer;
import eu.senla.shabalin.entity.Orders;
import eu.senla.shabalin.entity.Product;
import eu.senla.shabalin.utils.HibernateSessionFactoryUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TypeMismatchException;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class OrderProductTest {

    private static CustomerDao customerDao;
    private static OrdersDao ordersDao;
    private static ProductDao productDao;
    private static Session session;

    @BeforeAll
    public static void beforeAllTest(){
        session = HibernateSessionFactoryUtil.getSession();
        customerDao = new CustomerDaoImpl(session);
        ordersDao = new OrdersDaoImpl(session);
        productDao = new ProductDaoImpl(session);

        Customer customer1 = new Customer("Sergei", "Petrov", 25);
        Customer customer2 = new Customer("Vasia", "Sidorov", 30);
        Customer customer3 = new Customer("Vera", "Ivanova", 28);
        Customer customer4 = new Customer("Ivan", "Petrovivh", 50);
        Customer customer5 = new Customer("Andrei", "Andreev", 45);
        Customer customer6 = new Customer("Vasia", "Sidorov", 30);


        customer1.setId((Long) customerDao.create(customer1));
        customer2.setId((Long) customerDao.create(customer2));
        customer3.setId((Long) customerDao.create(customer3));
        customer4.setId((Long) customerDao.create(customer4));
        customer5.setId((Long) customerDao.create(customer5));
        customer6.setId((Long) customerDao.create(customer6));


        Product product1 = new Product("Phone", 300);
        Product product2 = new Product("TV", 600);
        Product product3 = new Product("Player Audio", 200);
        Product product4 = new Product("PC", 800);

        product1.setId((Long) productDao.create(product1));
        product2.setId((Long) productDao.create(product2));
        product3.setId((Long) productDao.create(product3));
        product4.setId((Long) productDao.create(product4));

        Orders order1 = new Orders(customer1, new Date(), new ArrayList(Arrays.asList(product1)));
        Orders order2 = new Orders(customer2, new Date(), new ArrayList(Arrays.asList(product2)));
        Orders order3 = new Orders(customer3, new Date(), new ArrayList(Arrays.asList(product3)));
        Orders order4 = new Orders(customer4, new Date(), new ArrayList(Arrays.asList(product4)));

        order1.setId((Long) ordersDao.create(order1));
        order2.setId((Long) ordersDao.create(order2));
        order3.setId((Long) ordersDao.create(order3));
        order4.setId((Long) ordersDao.create(order4));
    }

    @Test
    public void readCustomerTest() {
        long id = (long) customerDao.create(new Customer("VasiaTester", "Sidorov", 30));
        Customer customer = (Customer) customerDao.read(id);
        assertEquals("VasiaTester", customer.getFirstName());
    }

    @Test
    public void createCustomerTest() {
        Customer customer = new Customer("Name","LastName", 99);
        long id = (long) customerDao.create(customer);
        assertNotNull(id);
    }

    @Test
    public void deleteCustomerTest() {
        Customer customer = new Customer("Name","LastName", 99);
        customer.setId((long) customerDao.create(customer));
        customerDao.delete(customer);
        assertThrows(ObjectNotFoundException.class, () -> {
            Customer customerFromDb = ((Customer) customerDao.read(customer.getId()));
            customerFromDb.setId(1L);
        });

//        assertEquals(ObjectNotFoundException.class, thrown.getClass());
//        Customer customerFromDb = (Customer) customerDao.read(customer.getId());
//        System.out.println();
//        assertNull(customerFromDb);
    }

    @Test
    public void updateCustomerTest() {
        Customer customer = new Customer("Name","LastName", 99);
        customer.setId((Long) customerDao.create(customer));
        customer.setFirstName("Updated FirstName");
        customerDao.update(customer);
        Customer customerFromDb = (Customer) customerDao.read(customer.getId());
        assertEquals(customer.getFirstName(), customerFromDb.getFirstName());
    }

    @Test
    public void readAllCustomers() {
        assertTrue(customerDao.findAll().size()>0);
    }

    @AfterAll
    public static void clearAllTables() {
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("TRUNCATE customer, order_product, orders, product").executeUpdate();
        transaction.commit();
    }
}
