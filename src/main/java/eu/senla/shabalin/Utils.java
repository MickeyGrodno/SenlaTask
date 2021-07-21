package eu.senla.shabalin;

import eu.senla.shabalin.utils.EntityConvertor;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static eu.senla.shabalin.DataBaseConnector.getConnection;

public class Utils<T> {

    private PreparedStatement preparedStatementSetter(PreparedStatement statement, Field[] fields, T object) throws IllegalAccessException, SQLException, ParseException {
        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if(fields[i].getGenericType().getTypeName().toLowerCase().contains("string")) {
                statement.setString(i, fields[i].get(object).toString());
            } else {
                if(fields[i].getGenericType().getTypeName().toLowerCase().contains("date")) {
                    String str = fields[i].get(object).toString();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    statement.setDate(i, new Date((format.parse(fields[i].get(object).toString())).getTime()));
                } else {
                    if(fields[i].getGenericType().getTypeName().toLowerCase().contains("long")) {
                        statement.setLong(i, Long.parseLong(fields[i].get(object).toString()));
                    } else {
                        statement.setInt(i, Integer.parseInt(fields[i].get(object).toString()));
                    }
                }
            }
        }
        return statement;
    }

    private String entityToSqlDeleteQuery(T object) {
        String tableName = object.getClass().getSimpleName();
        return "delete from "+tableName+" where id = ?";
    }

    private String entityFieldToDBColumnNameConvertor(String value) {
        return value.replaceAll("(?<=[A-Za-z0-9])[A-Z]", "_$0").toLowerCase();
    }
    public String entityToSqlInsertQuery(T entity) {
        String tableName = entity.getClass().getSimpleName();
        Field[] fields = entity.getClass().getDeclaredFields();
        StringBuffer tableAndColumns = new StringBuffer();
        tableAndColumns.append("insert into ");
        tableAndColumns.append(tableName).append(" (");
        StringBuffer values = new StringBuffer();
        values.append(" values (");

        for(int i = 1; i<fields.length; i++) {
            if(fields.length-1==i) {
                values.append("?)");
                tableAndColumns.append(entityFieldToDBColumnNameConvertor(fields[i].getName())).append(")");
            } else {
                values.append("?, ");
                tableAndColumns.append(entityFieldToDBColumnNameConvertor(fields[i].getName())).append(", ");
            }
        }
        return tableAndColumns.append(values).toString();
    }

    public String entityToSqlUpdateQuery(T entity) {
        String tableName = entity.getClass().getSimpleName().toLowerCase();
        Field[] fields = entity.getClass().getDeclaredFields();
        StringBuffer tableAndColumns = new StringBuffer();
        tableAndColumns.append("update ").append(tableName).append(" set ");

        for(int i = 1; i<fields.length; i++) {
            if(fields.length-1==i) {
                tableAndColumns.append(entityFieldToDBColumnNameConvertor(fields[i].getName()))
                        .append(" = ? where id = ?");
            } else {
                tableAndColumns.append(entityFieldToDBColumnNameConvertor(fields[i].getName())).append(" = ?, ");
            }
        }
        return tableAndColumns.toString();
    }
    public ResultSet entityToSqlReadQuery(Long id, String tableName) throws ClassNotFoundException, SQLException {
        String sql = "select * from "+tableName+" where id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setLong(1, id);
        return statement.executeQuery();
    }

    public long insertEntityInDb(String sql, T object) throws ClassNotFoundException, SQLException, IllegalAccessException, ParseException {
        PreparedStatement statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        Field[] fields = object.getClass().getDeclaredFields();

        int affectedRows = preparedStatementSetter(statement, fields, object).executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
               return generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }



    public void updateEntityInDb(String sql, T object) throws ClassNotFoundException, SQLException, IllegalAccessException, ParseException {
        PreparedStatement statement = getConnection().prepareStatement(sql);
        Field[] fields = object.getClass().getDeclaredFields();
        int fieldsLength = fields.length;

        fields[0].setAccessible(true);
        statement.setLong(fieldsLength, Long.parseLong(fields[0].get(object).toString()));

        int affectedRows = preparedStatementSetter(statement, fields, object).executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Updating user failed, no rows affected.");
        }
    }


    public void deleteEntityFromDb(T object) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException {
        Field idField = object.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        long id = Long.parseLong(idField.get(object).toString());
        PreparedStatement statement = getConnection().prepareStatement(entityToSqlDeleteQuery(object));
        statement.setLong(1, id);
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Updating user failed, no rows affected.");
        }
    }

    public List<T> getAllEntityFromDb(Class clazz) throws ClassNotFoundException, SQLException {
        String name = clazz.getSimpleName();
        String tableName = clazz.getSimpleName().toLowerCase();
        PreparedStatement statement = getConnection().prepareStatement("select * from "+tableName);
        ResultSet resultSet = statement.executeQuery();
        List<T> entityList = new ArrayList<>();
        while (!resultSet.isLast()) {
            entityList.add((T) EntityConvertor.convertResultSetToEntity(resultSet, clazz));
        }
        return entityList;
    }
}
