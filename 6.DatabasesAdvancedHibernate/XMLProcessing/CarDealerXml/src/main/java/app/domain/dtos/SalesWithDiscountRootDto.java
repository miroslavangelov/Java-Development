package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesWithDiscountRootDto {
    @XmlElement(name = "sale")
    private List<SalesWithDiscountDto> salesWithDiscountDtos;

    public SalesWithDiscountRootDto() {
    }

    public List<SalesWithDiscountDto> getSalesWithDiscountDtos() {
        return salesWithDiscountDtos;
    }

    public void setSalesWithDiscountDtos(List<SalesWithDiscountDto> salesWithDiscountDtos) {
        this.salesWithDiscountDtos = salesWithDiscountDtos;
    }
}
