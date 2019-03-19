package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerWithSalesRootDto {
    @XmlElement(name = "customer")
    private List<CustomerWithSalesDto> customerWithSalesDtos;

    public CustomerWithSalesRootDto() {
    }

    public List<CustomerWithSalesDto> getCustomerWithSalesDtos() {
        return customerWithSalesDtos;
    }

    public void setCustomerWithSalesDtos(List<CustomerWithSalesDto> customerWithSalesDtos) {
        this.customerWithSalesDtos = customerWithSalesDtos;
    }
}
