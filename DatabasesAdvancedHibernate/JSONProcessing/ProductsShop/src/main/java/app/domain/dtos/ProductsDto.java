package app.domain.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductsDto {
    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    public ProductsDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
