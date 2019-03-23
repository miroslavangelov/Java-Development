package app.services;

import app.domain.dtos.CarDto;
import app.domain.dtos.SalesWithDiscountCarDto;
import app.domain.dtos.SalesWithDiscountDto;
import app.domain.dtos.SalesWithDiscountRootDto;
import app.domain.entities.Car;
import app.domain.entities.Customer;
import app.domain.entities.Part;
import app.domain.entities.Sale;
import app.repositories.CarRepository;
import app.repositories.CustomerRepository;
import app.repositories.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository, CarRepository carRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSales() {
        List<Customer> customers = this.customerRepository.findAll();
        List<Car> cars = this.carRepository.findAll();
        final Random random = new Random();
        final Double[] discounts = {0d, 0.05d, 0.1d, 0.15d, 0.2d, 0.3d, 0.4d, 0.5d};
        List<Sale> sales = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Sale sale = new Sale();
            sale.setCar(cars.get(random.nextInt(cars.size())));
            sale.setCustomer(customers.get(random.nextInt(customers.size())));
            sale.setDiscount(discounts[random.nextInt(discounts.length)]);
            sales.add(sale);
        }

        this.saleRepository.saveAll(sales);
    }

    @Override
    public SalesWithDiscountRootDto getSalesWithDiscounts() {
        List<Sale> sales = this.saleRepository.findAll();
        List<SalesWithDiscountDto> salesWithDiscountDtos = new ArrayList<>();

        for (Sale sale: sales) {
            SalesWithDiscountDto salesWithDiscountDto = new SalesWithDiscountDto();
            SalesWithDiscountCarDto carDto = this.modelMapper.map(sale.getCar(), SalesWithDiscountCarDto.class);
            salesWithDiscountDto.setCar(carDto);
            salesWithDiscountDto.setCustomerName(sale.getCustomer().getName());
            salesWithDiscountDto.setDiscount(sale.getDiscount());
            BigDecimal price = sale.getCar()
                    .getParts()
                    .stream()
                    .map(Part::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            salesWithDiscountDto.setPrice(price);
            salesWithDiscountDto.setPriceWithDiscount(price.multiply(BigDecimal.valueOf(1.0d - sale.getDiscount())));

            salesWithDiscountDtos.add(salesWithDiscountDto);
        }

        SalesWithDiscountRootDto salesWithDiscountRootDto = new SalesWithDiscountRootDto();
        salesWithDiscountRootDto.setSalesWithDiscountDtos(salesWithDiscountDtos);

        return salesWithDiscountRootDto;
    }
}
