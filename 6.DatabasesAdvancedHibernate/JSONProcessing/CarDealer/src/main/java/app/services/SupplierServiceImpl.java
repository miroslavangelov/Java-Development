package app.services;

import app.domain.dtos.LocalSuppliersDto;
import app.domain.dtos.SupplierSeedDto;
import app.domain.entities.Supplier;
import app.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSuppliers(SupplierSeedDto[] supplierSeedDtos) {
        for (SupplierSeedDto supplierSeedDto: supplierSeedDtos) {
            Supplier supplier = this.modelMapper.map(supplierSeedDto, Supplier.class);
            this.supplierRepository.saveAndFlush(supplier);
        }
    }

    @Override
    public List<LocalSuppliersDto> getLocalSuppliers() {
        List<Supplier> suppliers = this.supplierRepository.getSuppliersByImporterIsFalse();
        List<LocalSuppliersDto> localSuppliersDtos = new ArrayList<>();

        for (Supplier supplier: suppliers) {
            LocalSuppliersDto localSuppliersDto = this.modelMapper.map(supplier, LocalSuppliersDto.class);
            Integer partsCount = supplier.getParts().size();
            localSuppliersDto.setPartsCount(partsCount);
            localSuppliersDtos.add(localSuppliersDto);
        }

        return localSuppliersDtos;
    }
}
