package eu.senla.shabalin;

import eu.senla.shabalin.dao.CustomerDaoImpl;
import eu.senla.shabalin.dao.OrderProductDaoImpl;
import eu.senla.shabalin.dao.OrdersDaoImpl;
import eu.senla.shabalin.dao.ProductDaoImpl;
import eu.senla.shabalin.dao.interfaces.CustomerDao;
import eu.senla.shabalin.dao.interfaces.OrderProductDao;
import eu.senla.shabalin.dao.interfaces.OrdersDao;
import eu.senla.shabalin.dao.interfaces.ProductDao;
import eu.senla.shabalin.entity.Customer;
import eu.senla.shabalin.entity.OrderProduct;
import eu.senla.shabalin.entity.Orders;
import eu.senla.shabalin.entity.Product;
import eu.senla.shabalin.utils.Utils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderProductTest {
    private static CustomerDao customerDao = new CustomerDaoImpl();
    private static OrderProductDao orderProductDao = new OrderProductDaoImpl();
    private static OrdersDao ordersDao = new OrdersDaoImpl();
    private static ProductDao productDao = new ProductDaoImpl();
    private static SoftAssertions assertions = new SoftAssertions();
    @BeforeAll
    public static void beforeAllTest() throws SQLException, ParseException, ClassNotFoundException, IllegalAccessException {
        List<Customer> customers = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<Orders> ordersList = new ArrayList<>();
        List<OrderProduct> orderProductList = new ArrayList<>();
        List<Long> customerIdList = new ArrayList<>();
        List<Long> productIdList = new ArrayList<>();
        List<Long> orderIdList = new ArrayList<>();
        List<Long> orderProductIdList = new ArrayList<>();

        customers.add(new Customer("Sergei", "Petrov", 25));
        customers.add(new Customer("Vasia", "Sidorov", 30));
        customers.add(new Customer("Vera", "Ivanova", 28));
        customers.add(new Customer("Ivan", "Petrovivh", 50));
        customers.add(new Customer("Andrei", "Andreev", 45));
        customerDao.create(new Customer("Vasia", "Sidorov", 30));
        for(Customer customer : customers) {
            customerIdList.add(customerDao.create(customer));
        }
        products.add(new Product("Phone", 300));
        products.add(new Product("TV", 600));
        products.add(new Product("Player Audio", 200));
        products.add(new Product("PC", 800));
        for(Product product : products) {
            productIdList.add(productDao.create(product));
        }

        ordersList.add(new Orders(customerIdList.get(0), LocalDate.now()));
        ordersList.add(new Orders(customerIdList.get(1), LocalDate.now()));
        ordersList.add(new Orders(customerIdList.get(2), LocalDate.now()));
        ordersList.add(new Orders(customerIdList.get(3), LocalDate.now()));
        for(Orders order : ordersList) {
            orderIdList.add(ordersDao.create(order));
        }

        orderProductList.add(new OrderProduct(orderIdList.get(0), productIdList.get(0)));
        orderProductList.add(new OrderProduct(orderIdList.get(0), productIdList.get(1)));
        orderProductList.add(new OrderProduct(orderIdList.get(1), productIdList.get(1)));
        orderProductList.add(new OrderProduct(orderIdList.get(1), productIdList.get(3)));
        for(OrderProduct orderProduct : orderProductList) {
            orderProductIdList.add(orderProductDao.create(orderProduct));
        }
    }

    @Test
    public void createAndReadCustomerTest() throws SQLException, ParseException, ClassNotFoundException, IllegalAccessException {
        Customer customer = new Customer("Name","LastName", 99);
        Long customerId = customerDao.create(customer);
        Customer customerFromDb = (Customer) customerDao.read(customerId);
        assertions.assertThat(customerId).isNotNull();
        assertions.assertThat(customer.getLastName()).isEqualTo(customerFromDb.getLastName());
        assertions.assertAll();
    }

    @Test
    public void updateCustomerTest() throws SQLException, ClassNotFoundException, ParseException, IllegalAccessException {
        Customer customer = (Customer) customerDao.read(1L);
        customer.setAge(15L);
        customer.setFirstName("UpdatedName");
        customerDao.update(customer);
        Customer updatedCustomer = (Customer) customerDao.read(customer.getId());
        Assertions.assertEquals(customer.getFirstName(), updatedCustomer.getFirstName());
    }

    @Test
    public void deleteCustomerTest() throws SQLException, ParseException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        Customer customer = new Customer("Name","LastName", 99);
        Long customerId = customerDao.create(customer);
        customer.setId(customerId);
        customerDao.delete(customer);
        Throwable thrown = assertThrows(SQLException.class, () -> {
            customerDao.read(customerId);
        });
        assertEquals("Entity not found!", thrown.getMessage());
    }

    @Test
    public void getAllCustomersTest() throws SQLException, ClassNotFoundException {
        List<Customer> customers = customerDao.findAll();
        System.out.println();
        assertTrue(customers.size()>0);
    }

    @AfterAll
    public static void afterAllTest() throws SQLException, ClassNotFoundException {
        Utils utils = new Utils();
        utils.clearAllTables();
    }
}
