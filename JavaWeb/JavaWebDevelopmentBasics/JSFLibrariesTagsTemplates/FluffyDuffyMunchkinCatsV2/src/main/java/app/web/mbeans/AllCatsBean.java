package app.web.mbeans;

import app.domain.models.view.AllCatsModel;
import app.service.CatService;
import app.util.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class AllCatsBean {
    private List<AllCatsModel> cats;
    private CatService catService;
    private ModelMapper modelMapper;

    public AllCatsBean() {

    }

    @Inject
    public AllCatsBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.cats = this.catService.findAllCats()
            .stream().map(cat -> this.modelMapper.map(cat, AllCatsModel.class))
            .collect(Collectors.toList());
    }

    public List<AllCatsModel> getCats() {
        return cats;
    }

    public void setCats(List<AllCatsModel> cats) {
        this.cats = cats;
    }
}
