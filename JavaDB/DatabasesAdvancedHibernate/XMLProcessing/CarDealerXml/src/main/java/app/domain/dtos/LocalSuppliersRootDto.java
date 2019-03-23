package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSuppliersRootDto {
    @XmlElement(name = "supplier")
    private List<LocalSuppliersDto> localSuppliersDtos;

    public LocalSuppliersRootDto() {
    }

    public List<LocalSuppliersDto> getLocalSuppliersDtos() {
        return localSuppliersDtos;
    }

    public void setLocalSuppliersDtos(List<LocalSuppliersDto> localSuppliersDtos) {
        this.localSuppliersDtos = localSuppliersDtos;
    }
}
