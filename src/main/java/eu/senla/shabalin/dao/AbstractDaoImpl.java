package eu.senla.shabalin.dao;

import eu.senla.shabalin.dao.interfaces.AbstractDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class AbstractDaoImpl<T, PK extends Serializable> implements AbstractDao<T, PK> {

    private final Session session;
    private Class<T> type;
    private Transaction transaction;

    public AbstractDaoImpl(Session session, Class<T> type) {
        this.session = session;
        this.type = type;
    }

    public Session getSession() {
        return session;
    }

    public PK create(T o) {
        transaction = session.beginTransaction();
        Long id = (Long) getSession().save(o);
        transaction.commit();
        return (PK) id;
    }

    public T read(PK id) {
        return (T) getSession().load(type, id);
    }

    public void update(T o) {
        transaction = session.beginTransaction();
        getSession().update(o);
        transaction.commit();
    }

    public void delete(T o) {
        transaction = session.beginTransaction();
        getSession().delete(o);
        transaction.commit();

    }

    public List<T> findAll() {
        return getSession().createQuery("select x from " + type.getName() + " x", type).getResultList();
    }
}
