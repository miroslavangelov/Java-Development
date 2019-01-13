package app.controllers;

import app.domain.dtos.*;
import app.services.*;
import app.utils.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class CarDealerController implements CommandLineRunner {
    private final static String SUPPLIER_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealerXml\\src\\main\\resources\\files\\suppliers.xml";
    private final static String PART_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealerXml\\src\\main\\resources\\files\\parts.xml";
    private final static String CAR_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealerXml\\src\\main\\resources\\files\\cars.xml";
    private final static String CUSTOMER_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealerXml\\src\\main\\resources\\files\\customers.xml";
    private final static String ORDERED_CUSTOMERS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealerXml\\src\\main\\resources\\files\\output\\ordered-customers.xml";
    private final static String TOYOTA_CARS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealerXml\\src\\main\\resources\\files\\output\\toyota-cars.xml";
    private final static String LOCAL_SUPPLIERS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealerXml\\src\\main\\resources\\files\\output\\local-suppliers.xml";
    private final static String CARS_AND_PARTS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealerXml\\src\\main\\resources\\files\\output\\cars-and-parts.xml";
    private final static String CUSTOMERS_TOTAL_SALES_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealerXml\\src\\main\\resources\\files\\output\\customers-total-sales.xml";
    private final static String SALES_WITH_DISCOUNTS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealerXml\\src\\main\\resources\\files\\output\\sales-discounts.xml";

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final XmlParser xmlParser;

    @Autowired
    public CarDealerController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService, XmlParser xmlParser) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedSuppliers();
        this.seedParts();
        this.seedCustomers();
        this.seedCars();
        this.saleService.seedSales();

        this.orderedCustomers();
        this.toyotaCars();
        this.localSuppliers();
        this.carsAndParts();
        this.customersWithSales();
        this.salesWithDiscounts();
    }

    private void seedSuppliers() throws IOException, JAXBException {
        SupplierSeedRootDto supplierSeedRootDto = this.xmlParser
                .parseXml(SupplierSeedRootDto.class, SUPPLIER_FILE_PATH);

        this.supplierService.seedSuppliers(supplierSeedRootDto.getSupplierSeedDtos());
    }

    private void seedParts() throws IOException, JAXBException {
        PartSeedRootDto partSeedRootDto = this.xmlParser
                .parseXml(PartSeedRootDto.class, PART_FILE_PATH);

        this.partService.seedParts(partSeedRootDto.getPartSeedDtos());
    }

    private void seedCustomers() throws IOException, JAXBException {
        CustomerSeedRootDto customerSeedRootDto = this.xmlParser
                .parseXml(CustomerSeedRootDto.class, CUSTOMER_FILE_PATH);

        this.customerService.seedCustomers(customerSeedRootDto.getCustomerSeedDtos());
    }

    private void seedCars() throws IOException, JAXBException {
        CarSeedRootDto carSeedRootDto = this.xmlParser
                .parseXml(CarSeedRootDto.class, CAR_FILE_PATH);

        this.carService.seedCars(carSeedRootDto.getCarSeedDtos());
    }

    private void orderedCustomers() throws JAXBException {
        OrderedCustomersRootDto orderedCustomersRootDto = this.customerService.getOrderedCustomers();

        this.xmlParser.exportToXml(orderedCustomersRootDto, OrderedCustomersRootDto.class, ORDERED_CUSTOMERS_FILE_PATH);
    }

    private void toyotaCars() throws JAXBException {
        ToyotaCarsRootDto toyotaCarsRootDto = this.carService.getToyotaCars();

        this.xmlParser.exportToXml(toyotaCarsRootDto, ToyotaCarsRootDto.class, TOYOTA_CARS_FILE_PATH);
    }

    private void localSuppliers() throws JAXBException {
        LocalSuppliersRootDto localSuppliersRootDto = this.supplierService.getLocalSuppliers();

        this.xmlParser.exportToXml(localSuppliersRootDto, LocalSuppliersRootDto.class, LOCAL_SUPPLIERS_FILE_PATH);
    }

    private void carsAndParts() throws JAXBException {
        CarsAndPartsRootDto carsAndPartsRootDto = this.carService.getCarsAndParts();

        this.xmlParser.exportToXml(carsAndPartsRootDto, CarsAndPartsRootDto.class, CARS_AND_PARTS_FILE_PATH);
    }

    private void customersWithSales() throws JAXBException {
        CustomerWithSalesRootDto customerWithSalesRootDto = this.customerService.getCustomerWithSalesDto();

        this.xmlParser.exportToXml(customerWithSalesRootDto, CustomerWithSalesRootDto.class, CUSTOMERS_TOTAL_SALES_FILE_PATH);
    }

    private void salesWithDiscounts() throws JAXBException {
        SalesWithDiscountRootDto salesWithDiscountRootDto = this.saleService.getSalesWithDiscounts();

        this.xmlParser.exportToXml(salesWithDiscountRootDto, SalesWithDiscountRootDto.class, SALES_WITH_DISCOUNTS_FILE_PATH);
    }
}
