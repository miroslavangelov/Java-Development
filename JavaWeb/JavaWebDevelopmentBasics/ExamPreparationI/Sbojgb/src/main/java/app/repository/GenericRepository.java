package app.repository;

public interface GenericRepository<E, ID> {
    E save(E entity);

    E findById(ID id);
}
