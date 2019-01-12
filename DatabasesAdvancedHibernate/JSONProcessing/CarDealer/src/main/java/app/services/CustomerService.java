package app.services;

import app.domain.dtos.CustomerSeedDto;
import app.domain.dtos.CustomerWithSalesDto;
import app.domain.dtos.OrderedCustomersDto;

import java.util.List;


public interface CustomerService {
    void seedCustomers(CustomerSeedDto[] customerSeedDtos);

    OrderedCustomersDto[] getOrderedCustomers();

    List<CustomerWithSalesDto> getCustomerWithPurchasesDto();
}
