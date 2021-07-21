package eu.senla.shabalin.dao.interfaces;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface AbstractDao <T extends Serializable> {
    Long create(T newInstance) throws ClassNotFoundException, SQLException, IllegalAccessException, ParseException;

    T read(Long id) throws SQLException, ClassNotFoundException;

    void update(T transientObject) throws SQLException, ClassNotFoundException, IllegalAccessException, ParseException;

    void delete(T persistentObject) throws SQLException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException;

    List<T> findAll() throws SQLException, ClassNotFoundException;
}
