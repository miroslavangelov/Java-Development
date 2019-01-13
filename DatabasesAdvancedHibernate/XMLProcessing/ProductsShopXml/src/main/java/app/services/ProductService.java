package app.services;

import app.domain.dtos.ProductInRangeRootDto;
import app.domain.dtos.ProductSeedDto;

import java.math.BigDecimal;

public interface ProductService {
    void seedProducts(ProductSeedDto[] productSeedDtos);

    ProductInRangeRootDto getProductsInRange(BigDecimal startRange, BigDecimal endRange);
}
