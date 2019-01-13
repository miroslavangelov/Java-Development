package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedRootDto {
    @XmlElement(name = "supplier")
    private SupplierSeedDto[] supplierSeedDtos;

    public SupplierSeedRootDto() {
    }

    public SupplierSeedDto[] getSupplierSeedDtos() {
        return supplierSeedDtos;
    }

    public void setSupplierSeedDtos(SupplierSeedDto[] supplierSeedDtos) {
        this.supplierSeedDtos = supplierSeedDtos;
    }
}
