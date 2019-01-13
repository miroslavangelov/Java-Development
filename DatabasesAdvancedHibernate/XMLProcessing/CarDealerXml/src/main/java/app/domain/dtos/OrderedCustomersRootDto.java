package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomersRootDto {
    @XmlElement(name = "customer")
    private OrderedCustomersDto[] orderedCustomersDtos;

    public OrderedCustomersRootDto() {
    }

    public OrderedCustomersDto[] getOrderedCustomersDtos() {
        return orderedCustomersDtos;
    }

    public void setOrderedCustomersDtos(OrderedCustomersDto[] orderedCustomersDtos) {
        this.orderedCustomersDtos = orderedCustomersDtos;
    }
}
