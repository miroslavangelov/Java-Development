package app.web.controllers;

import app.domain.entities.annotations.Magnitude;
import app.domain.entities.annotations.Mutation;
import app.domain.models.binding.VirusBindingModel;
import app.domain.models.service.CapitalServiceModel;
import app.domain.models.view.CapitalListViewModel;
import app.domain.models.view.VirusListViewModel;
import app.service.CapitalService;
import app.service.VirusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {
    private final VirusService virusService;
    private final CapitalService capitalService;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusController(VirusService virusService, CapitalService capitalService, ModelMapper modelMapper) {
        this.virusService = virusService;
        this.capitalService = capitalService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public ModelAndView addView(@ModelAttribute(name = "virus") VirusBindingModel virusBindingModel, ModelAndView modelAndView) {
        modelAndView.addObject("virus", virusBindingModel);
        this.addObjectsInModelAndView(modelAndView);

        return super.view("add-virus", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MODERATOR')")
    public ModelAndView addAction(@Valid @ModelAttribute(name = "virus") VirusBindingModel virusBindingModel,
                                  BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("virus", virusBindingModel);
            this.addObjectsInModelAndView(modelAndView);

            return super.view("add-virus", modelAndView);
        }

        this.virusService.saveVirus(virusBindingModel);

        return super.redirect("/viruses/show");
    }

    @GetMapping("/show")
    public ModelAndView list() {
        return super.view("show");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteVirus(@PathVariable("id") String id, ModelAndView modelAndView) {
        VirusBindingModel virus = this.virusService.findVirusByIdForVirusForm(id);
        modelAndView.addObject("virus", virus);

        this.addObjectsInModelAndView(modelAndView);

        return super.view("delete-virus", modelAndView);
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteVirusConfirm(@PathVariable("id") String id) {
        this.virusService.deleteVirus(id);

        return super.redirect("/viruses/show");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editView(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        VirusBindingModel virus = this.virusService.findVirusByIdForVirusForm(id);
        modelAndView.addObject("virus", virus);

        this.addObjectsInModelAndView(modelAndView);

        return super.view("edit-virus", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editAction(@PathVariable(name = "id") String id, @Valid @ModelAttribute(name = "virus") VirusBindingModel virusBindingModel,
                                   BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("virus", virusBindingModel);
            this.addObjectsInModelAndView(modelAndView);

            return super.view("edit-virus", modelAndView);
        }

        this.virusService.saveVirus(virusBindingModel);

        return super.redirect("/viruses/show");
    }

    @GetMapping("/capitals-list")
    public ModelAndView capitalsView(ModelAndView modelAndView) {
        List<CapitalListViewModel> capitals = this.capitalService.findAllCapitals().stream()
                .map(capital -> this.modelMapper.map(capital, CapitalListViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("capitals", capitals);

        return super.view("capitals", modelAndView);
    }

    @GetMapping("/viruses-list")
    public ModelAndView virusesView(ModelAndView modelAndView) {
        List<VirusListViewModel> viruses = this.virusService.findAllViruses().stream()
                .map(virus -> this.modelMapper.map(virus, VirusListViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("viruses", viruses);

        return super.view("viruses", modelAndView);
    }

    private void addObjectsInModelAndView(ModelAndView modelAndView) {
        modelAndView.addObject("mutations", Mutation.values());
        modelAndView.addObject("magnitudes", Magnitude.values());
        modelAndView.addObject("capitals", this.capitalService.findAllCapitals().stream()
                .sorted(Comparator.comparing(CapitalServiceModel::getName))
                .collect(Collectors.toList()));
    }
}
