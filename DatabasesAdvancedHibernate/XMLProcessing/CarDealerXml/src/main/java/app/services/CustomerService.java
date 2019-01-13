package app.services;

import app.domain.dtos.CustomerSeedDto;
import app.domain.dtos.CustomerWithSalesDto;
import app.domain.dtos.CustomerWithSalesRootDto;
import app.domain.dtos.OrderedCustomersRootDto;

import java.util.List;


public interface CustomerService {
    void seedCustomers(CustomerSeedDto[] customerSeedDtos);

    OrderedCustomersRootDto getOrderedCustomers();

    CustomerWithSalesRootDto getCustomerWithSalesDto();
}
