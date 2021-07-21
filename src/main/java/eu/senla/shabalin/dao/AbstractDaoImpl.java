package eu.senla.shabalin.dao;

import eu.senla.shabalin.Utils;
import eu.senla.shabalin.dao.interfaces.AbstractDao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class AbstractDaoImpl<T, PK extends Serializable> implements AbstractDao<T, Long> {
    private Class<T> type;
    private Utils<T> utils = new Utils<T>();
    public AbstractDaoImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public Long create(T newInstance) throws ClassNotFoundException, SQLException, IllegalAccessException {

        return utils.insertEntityInDb(utils.entityToSqlInsertQuery(newInstance), newInstance);
    }

    @Override
    public T read(Long id) throws SQLException, ClassNotFoundException {
        utils.entityToSqlReadQuery(id, type.getSimpleName().toLowerCase());
        return null;
    }

    @Override
    public void update(T transientObject) {

    }

    @Override
    public void delete(T persistentObject) {

    }

    @Override
    public List<T> findAll(Class clazz) {
        return null;
    }
}
