package app.service;

import app.domain.entities.Capital;
import app.domain.entities.Virus;
import app.domain.models.binding.VirusBindingModel;
import app.domain.models.service.VirusServiceModel;
import app.repository.CapitalRepository;
import app.repository.VirusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {
    private final VirusRepository virusRepository;
    private final CapitalRepository capitalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, CapitalRepository capitalRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<VirusServiceModel> findAllViruses() {
        return this.virusRepository.findAll()
                .stream()
                .map(virus -> this.modelMapper.map(virus, VirusServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteVirus(String id) {
        try {
            this.virusRepository.deleteById(id);

            return true;
        } catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public void saveVirus(VirusBindingModel virusBindingModel) {
        Virus virus = this.modelMapper.map(virusBindingModel, Virus.class);

        List<Capital> capitals = new ArrayList<>();
        for (String capitalId : virusBindingModel.getCapitalIds()) {
            Capital capital = this.capitalRepository.findById(capitalId).orElse(null);

            if (capital == null) {
                continue;
            }

            capitals.add(capital);
        }

        virus.setCapitals(capitals);
        this.virusRepository.saveAndFlush(virus);
    }

    @Override
    public VirusBindingModel findVirusByIdForVirusForm(String id) {
        Virus virus = this.virusRepository.findById(id).orElse(null);
        if (virus == null) {
            throw new IllegalArgumentException("Invalid id!");
        }

        VirusBindingModel virusBindingModel = this.modelMapper.map(virus, VirusBindingModel.class);
        List<String> capitalIds = virus.getCapitals().stream().map(Capital::getId).collect(Collectors.toList());
        virusBindingModel.setCapitalIds(capitalIds);

        return virusBindingModel;
    }
}
