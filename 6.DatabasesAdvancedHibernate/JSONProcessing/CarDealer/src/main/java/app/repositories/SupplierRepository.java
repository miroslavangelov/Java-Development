package app.repositories;

import app.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    @Query(value = "SELECT s.* FROM suppliers AS s WHERE s.is_importer = FALSE", nativeQuery = true)
    List<Supplier> getSuppliersByImporterIsFalse();
}
