package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesByProductsCountRootDto {
    @XmlElement(name = "category")
    private List<CategoriesByProductsCountDto> categoriesByProductsCountDtos;

    public CategoriesByProductsCountRootDto() {
    }

    public List<CategoriesByProductsCountDto> getCategoriesByProductsCountDtos() {
        return categoriesByProductsCountDtos;
    }

    public void setCategoriesByProductsCountDtos(List<CategoriesByProductsCountDto> categoriesByProductsCountDtos) {
        this.categoriesByProductsCountDtos = categoriesByProductsCountDtos;
    }
}
