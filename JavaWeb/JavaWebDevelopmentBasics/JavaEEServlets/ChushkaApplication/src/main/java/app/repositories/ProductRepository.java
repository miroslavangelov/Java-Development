package app.repositories;

import app.domain.entities.Product;

public interface ProductRepository extends GenericRepository<Product, Long> {
    Product findByName(String name);
}
