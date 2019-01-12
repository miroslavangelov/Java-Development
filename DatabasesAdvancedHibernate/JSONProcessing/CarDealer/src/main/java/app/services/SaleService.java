package app.services;

import app.domain.dtos.SalesWithDiscountDto;

import java.util.List;

public interface SaleService {
    void seedSales();

    List<SalesWithDiscountDto> getSalesWithDiscounts();
}
