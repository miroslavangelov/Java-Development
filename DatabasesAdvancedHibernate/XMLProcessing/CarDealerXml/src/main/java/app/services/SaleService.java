package app.services;

import app.domain.dtos.SalesWithDiscountRootDto;

public interface SaleService {
    void seedSales();

    SalesWithDiscountRootDto getSalesWithDiscounts();
}
