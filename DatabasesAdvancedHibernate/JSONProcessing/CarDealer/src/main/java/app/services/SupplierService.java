package app.services;

import app.domain.dtos.LocalSuppliersDto;
import app.domain.dtos.SupplierSeedDto;

import java.util.List;

public interface SupplierService {
    void seedSuppliers(SupplierSeedDto[] supplierSeedDtos);

    List<LocalSuppliersDto> getLocalSuppliers();
}
