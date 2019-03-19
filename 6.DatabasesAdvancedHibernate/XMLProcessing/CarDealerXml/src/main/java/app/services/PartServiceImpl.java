package app.services;

import app.domain.dtos.PartSeedDto;
import app.domain.entities.Part;
import app.domain.entities.Supplier;
import app.repositories.PartRepository;
import app.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedParts(PartSeedDto[] partSeedDtos) {
        for (PartSeedDto partSeedDto: partSeedDtos) {
            Part part = this.modelMapper.map(partSeedDto, Part.class);
            Supplier supplier = this.getRandomSupplier();
            part.setSupplier(supplier);

            this.partRepository.saveAndFlush(part);
        }
    }

    private Supplier getRandomSupplier() {
        Random random = new Random();
        int randomId = random.nextInt((int) (this.supplierRepository.count() - 1)) + 1;

        return this.supplierRepository.getOne(randomId);
    }
}
