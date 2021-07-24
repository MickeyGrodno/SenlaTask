package eu.senla.shabalin.dao.interfaces;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao <T, PK extends Serializable> {
    PK create(T newInstance);

    T read(PK id);

    void update(T transientObject);

    void delete(T persistentObject);

    List<T> findAll();
}
