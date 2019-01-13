package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesWithDiscountDto {
    @XmlElement(name = "car")
    private SalesWithDiscountCarDto car;

    @XmlElement(name = "customer-name")
    private String customerName;

    @XmlElement(name = "discount")
    private Double discount;

    @XmlElement(name = "price")
    private BigDecimal price;

    @XmlElement(name = "price-with-discount")
    private BigDecimal priceWithDiscount;

    public SalesWithDiscountDto() {
    }

    public SalesWithDiscountCarDto getCar() {
        return car;
    }

    public void setCar(SalesWithDiscountCarDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
