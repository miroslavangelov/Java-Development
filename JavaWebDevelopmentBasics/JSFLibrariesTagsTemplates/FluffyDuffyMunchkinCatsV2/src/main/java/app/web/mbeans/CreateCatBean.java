package app.web.mbeans;

import app.domain.models.binding.CatCreateBindingModel;
import app.domain.models.service.CatServiceModel;
import app.service.CatService;
import app.util.ModelMapper;


import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CreateCatBean {
    private CatCreateBindingModel catCreateBindingModel;
    private CatService catService;
    private ModelMapper modelMapper;

    public CreateCatBean() {
        this.catCreateBindingModel = new CatCreateBindingModel();
    }

    @Inject
    public CreateCatBean(CatService catService, ModelMapper modelMapper) {
        this();
        this.catService = catService;
        this.modelMapper = modelMapper;
    }

    public CatCreateBindingModel getCatCreateBindingModel() {
        return catCreateBindingModel;
    }

    public void setCatCreateBindingModel(CatCreateBindingModel catCreateBindingModel) {
        this.catCreateBindingModel = catCreateBindingModel;
    }

    public void create() throws IOException {
        this.catService.saveCat(this.modelMapper.map(this.catCreateBindingModel, CatServiceModel.class));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/");
    }
}
