package app.services;

import app.domain.dtos.CustomerSeedDto;
import app.domain.dtos.CustomerWithSalesDto;
import app.domain.dtos.OrderedCustomersDto;
import app.domain.entities.Customer;
import app.domain.entities.Part;
import app.domain.entities.Sale;
import app.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCustomers(CustomerSeedDto[] customerSeedDtos) {
        for (CustomerSeedDto customerSeedDto: customerSeedDtos) {
            Customer customer = this.modelMapper.map(customerSeedDto, Customer.class);
            this.customerRepository.saveAndFlush(customer);
        }
    }

    @Override
    public OrderedCustomersDto[] getOrderedCustomers() {
        List<Customer> customers = this.customerRepository.getCustomersOrderedByBirthDate();

        return this.modelMapper.map(customers, OrderedCustomersDto[].class);
    }

    @Override
    public List<CustomerWithSalesDto> getCustomerWithPurchasesDto() {
        List<Customer> customers = this.customerRepository.getAllCustomerWithSales();
        List<CustomerWithSalesDto> customerWithSalesDtos = new ArrayList<>();

        for (Customer customer: customers) {
            CustomerWithSalesDto customerWithSalesDto = new CustomerWithSalesDto();
            customerWithSalesDto.setFullName(customer.getName());
            customerWithSalesDto.setBoughtCars(customer.getSales().size());

            BigDecimal moneySpent = customer.getSales()
                    .stream()
                    .map(sale -> sale.getCar()
                            .getParts()
                            .stream()
                            .map(Part::getPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                    )
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            customerWithSalesDto.setSpentMoney(moneySpent);
            customerWithSalesDtos.add(customerWithSalesDto);
        }

        customerWithSalesDtos = customerWithSalesDtos.stream()
                .sorted((c1, c2) -> {
                    int cmp = c2.getSpentMoney().compareTo(c1.getSpentMoney());
                    if (cmp == 0) {
                        cmp = c2.getBoughtCars().compareTo(c1.getBoughtCars());
                    }
                    return cmp;
                })
                .collect(Collectors.toList());

        return customerWithSalesDtos;
    }
}
