package app.service;

import app.domain.entities.Capital;
import app.domain.models.service.CapitalServiceModel;
import app.repository.CapitalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapitalServiceImpl implements CapitalService {
    private final CapitalRepository capitalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalRepository, ModelMapper modelMapper) {
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CapitalServiceModel> findAllCapitals() {
        return this.capitalRepository.findAllCapitals().stream()
                .map(capital -> this.modelMapper.map(capital, CapitalServiceModel.class))
                .collect(Collectors.toList());
    }
}
