package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductBuyerRootDto {
    @XmlElement(name = "product")
    private List<SoldProductBuyerDto> soldProductBuyerDtos;

    public SoldProductBuyerRootDto() {
    }

    public List<SoldProductBuyerDto> getSoldProductBuyerDtos() {
        return soldProductBuyerDtos;
    }

    public void setSoldProductBuyerDtos(List<SoldProductBuyerDto> soldProductBuyerDtos) {
        this.soldProductBuyerDtos = soldProductBuyerDtos;
    }
}
