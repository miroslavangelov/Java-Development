package app.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedDto {
    @XmlElement(name = "name")
    private String name;

    public CategorySeedDto() {
    }

    @NotNull
    @Size(min = 3, max = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
