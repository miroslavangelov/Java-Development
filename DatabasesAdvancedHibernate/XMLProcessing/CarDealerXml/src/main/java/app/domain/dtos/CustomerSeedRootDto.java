package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSeedRootDto {
    @XmlElement(name = "customer")
    private CustomerSeedDto[] customerSeedDtos;

    public CustomerSeedRootDto() {
    }

    public CustomerSeedDto[] getCustomerSeedDtos() {
        return customerSeedDtos;
    }

    public void setCustomerSeedDtos(CustomerSeedDto[] customerSeedDtos) {
        this.customerSeedDtos = customerSeedDtos;
    }
}
