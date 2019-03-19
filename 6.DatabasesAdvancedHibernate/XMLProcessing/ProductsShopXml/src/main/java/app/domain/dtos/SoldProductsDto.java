package app.domain.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsDto {
    @XmlAttribute(name = "count")
    private int count;

    @XmlElement(name = "product")
    private List<ProductsDto> products;

    public SoldProductsDto() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductsDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsDto> products) {
        this.products = products;
    }
}
