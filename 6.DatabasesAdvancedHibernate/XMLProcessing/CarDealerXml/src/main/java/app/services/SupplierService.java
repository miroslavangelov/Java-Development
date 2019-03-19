package app.services;

import app.domain.dtos.LocalSuppliersRootDto;
import app.domain.dtos.SupplierSeedDto;

public interface SupplierService {
    void seedSuppliers(SupplierSeedDto[] supplierSeedDtos);

    LocalSuppliersRootDto getLocalSuppliers();
}
