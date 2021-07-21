package eu.senla.shabalin;

import eu.senla.shabalin.dao.OrderDaoImpl;
import eu.senla.shabalin.entity.Orders;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;

import static eu.senla.shabalin.DataBaseConnector.getConnection;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, ParseException, NoSuchFieldException {

        Orders orders = new Orders();
        orders.setId(1);
        OrderDaoImpl dao = new OrderDaoImpl();
        dao.delete(orders);
//
//
//        CustomerDaoImpl customerDao = new CustomerDaoImpl();
//        Customer customer = (Customer) customerDao.read(2L);
//        System.out.println(customer.getId());
//        System.out.println(customer.getFirstName());
//        System.out.println(customer.getLastName());
//        System.out.println(customer.getAge());
//
//        PreparedStatement statement = getConnection().prepareStatement("update orders set customer_id = 2, order_date = '' where id = 1");
//        int resultSet = statement.executeUpdate();



//        PreparedStatement preparedStatement;
//        preparedStatement = getConnection().prepareStatement("select * from users where id = ?");
//        preparedStatement.setInt(1,1);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()) {
//            System.out.println(resultSet.getInt("id"));
//            System.out.println(resultSet.getString("first_name"));
//        }
//        preparedStatement.close();
    }
}
