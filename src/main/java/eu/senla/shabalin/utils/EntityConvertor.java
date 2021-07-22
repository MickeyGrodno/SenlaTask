package eu.senla.shabalin.utils;

import eu.senla.shabalin.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EntityConvertor {
    public static <T> Entity convertResultSetToEntity(ResultSet resultSet, Class<T> clazz) throws SQLException {
        if (resultSet.next()) {
            if (clazz.getSimpleName().equals(Customer.class.getSimpleName())) {
                return new Customer(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getLong("age"));
            } else {
                if (clazz.getSimpleName().equals(Orders.class.getSimpleName())) {
                    return new Orders(
                            resultSet.getLong("id"),
                            resultSet.getLong("user_id"),
                            LocalDate.parse((CharSequence) resultSet.getDate("order_date")));
                } else {
                    if(clazz.getSimpleName().equals(Product.class.getSimpleName())) {
                        return new Product(
                                resultSet.getLong("id"),
                                resultSet.getString("product_name"),
                                resultSet.getLong("cost"));
                    } else {
                        return new OrderProduct(
                                resultSet.getLong("id"),
                                resultSet.getLong("order_id"),
                                resultSet.getLong("product_id"));
                    }
                }
            }
        } else {
            throw new SQLException("Entity not found!");
        }
    }
}

