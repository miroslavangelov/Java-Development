package app.repositories;

import java.util.List;

public interface GenericRepository<E, K> {
    E save(E entity);

    E findById(K id);

    List<E> findAll();

    void deleteById(K id);
}