package app.services;

import app.domain.dtos.ProductInRangeDto;
import app.domain.dtos.ProductSeedDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts(ProductSeedDto[] productSeedDtos);

    List<ProductInRangeDto> getProductsInRange(BigDecimal startRange, BigDecimal endRange);
}
