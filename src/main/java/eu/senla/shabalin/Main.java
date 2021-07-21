package eu.senla.shabalin;

import eu.senla.shabalin.dao.ProductDaoImpl;
import eu.senla.shabalin.entity.Product;

import java.sql.*;

import static eu.senla.shabalin.DataBaseConnector.getConnection;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException {
//        PreparedStatement statement = getConnection().prepareStatement("select * from product where id = 1");
//        ResultSet resultSet = statement.executeQuery();
        ProductDaoImpl productDao = new ProductDaoImpl();
        productDao.read(2L);



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
