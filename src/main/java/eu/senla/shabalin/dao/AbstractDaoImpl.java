package eu.senla.shabalin.dao;

import eu.senla.shabalin.Utils;
import eu.senla.shabalin.dao.interfaces.AbstractDao;
import eu.senla.shabalin.utils.EntityConvertor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class AbstractDaoImpl<T, PK extends Serializable> implements AbstractDao<T, Long> {
    private Class<T> type;
    private Utils<T> utils = new Utils<T>();
    public AbstractDaoImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public Long create(T newInstance) throws ClassNotFoundException, SQLException, IllegalAccessException, ParseException {

        return utils.insertEntityInDb(utils.entityToSqlInsertQuery(newInstance), newInstance);
    }

    @Override
    public T read(Long id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = utils.entityToSqlReadQuery(id, type.getSimpleName().toLowerCase());
        return (T) EntityConvertor.convertResultSetToEntity(resultSet, type);
    }

    @Override
    public void update(T transientObject) throws SQLException, ClassNotFoundException, IllegalAccessException, ParseException {
        utils.updateEntityInDb(utils.entityToSqlUpdateQuery(transientObject), transientObject);
    }

    @Override
    public void delete(T transientObject) throws SQLException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        utils.deleteEntityFromDb(transientObject);
    }

    @Override
    public List<T> findAll(Class clazz) {
        return null;
    }
}
