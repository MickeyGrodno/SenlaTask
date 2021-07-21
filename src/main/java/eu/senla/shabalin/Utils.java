package eu.senla.shabalin;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import static eu.senla.shabalin.DataBaseConnector.getConnection;

public class Utils<T> {
    public String entityClassToShortClassNameBuilder(T entity) {
        return entity.getClass().getSimpleName().toLowerCase();
    }

    public String fieldTypeNameToShotStringBuilder(Field field) {
        String[] classNameArray = field.getGenericType().getTypeName().split("\\.");
        if(classNameArray.length < 1) {
            return classNameArray[0];
        } else {
            return classNameArray[classNameArray.length-1].toLowerCase();
        }
    }

    public String entityFieldToDBFieldConvertor(String value) {
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
                tableAndColumns.append(entityFieldToDBFieldConvertor(fields[i].getName())).append(")");
            } else {
                values.append("?, ");
                tableAndColumns.append(entityFieldToDBFieldConvertor(fields[i].getName())).append(", ");
            }
        }
        return tableAndColumns.append(values).toString();
    }

    public long insertEntityInDb(String sql, T object) throws ClassNotFoundException, SQLException, IllegalAccessException {
        PreparedStatement statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        Field[] fields = object.getClass().getDeclaredFields();

        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if(fields[i].getGenericType().getTypeName().toLowerCase().contains("string")) {
                statement.setString(i, fields[i].get(object).toString());
            } else {
                if(fields[i].getGenericType().getTypeName().toLowerCase().contains("date")) {
                    statement.setDate(i, java.sql.Date.valueOf(fields[i].get(object).toString()));
                } else {
                    if(fields[i].getGenericType().getTypeName().toLowerCase().contains("long")) {
                        statement.setLong(i, Long.parseLong(fields[i].get(object).toString()));
                    } else {
                        statement.setInt(i, Integer.parseInt(fields[i].get(object).toString()));
                    }
                }
            }
        }
        int affectedRows = statement.executeUpdate();

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

    public String entityToSqlReadQuery(Long id, String tableName) throws ClassNotFoundException, SQLException {
        String sql = "select * from "+tableName+" where id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        System.out.println();
        return null;
    }
}
