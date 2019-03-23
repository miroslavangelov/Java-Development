package app.service;

import app.domain.models.binding.VirusBindingModel;
import app.domain.models.service.VirusServiceModel;

import java.util.List;

public interface VirusService {
    List<VirusServiceModel> findAllViruses();

    boolean deleteVirus(String id);

    void saveVirus(VirusBindingModel virusBindingModel);

    VirusBindingModel findVirusByIdForVirusForm(String id);
}
