package app.domain.dtos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedRootDto {
    @XmlElement(name = "category")
    private CategorySeedDto[] categorySeedDtos;

    public CategorySeedRootDto() {
    }

    public CategorySeedDto[] getCategorySeedDtos() {
        return categorySeedDtos;
    }

    public void setCategorySeedDtos(CategorySeedDto[] categorySeedDtos) {
        this.categorySeedDtos = categorySeedDtos;
    }
}
