package app.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class SoldProductsDto {
    @Expose
    private int count;

    @Expose
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
