package eu.senla.shabalin.dao.interfaces;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface AbstractDao <T, PK extends Serializable> {
    PK create(T newInstance) throws ClassNotFoundException, SQLException, IllegalAccessException;

    T read(PK id) throws SQLException, ClassNotFoundException;

    void update(T transientObject);

    void delete(T persistentObject);

    List<T> findAll(Class clazz);
}
