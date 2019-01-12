package app.controllers;

import app.domain.dtos.*;
import app.services.*;
import app.utils.FileUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class CarDealerController implements CommandLineRunner {
    private final static String SUPPLIER_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealer\\src\\main\\resources\\files\\suppliers.json";
    private final static String PART_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealer\\src\\main\\resources\\files\\parts.json";
    private final static String CAR_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealer\\src\\main\\resources\\files\\cars.json";
    private final static String CUSTOMER_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealer\\src\\main\\resources\\files\\customers.json";
    private final static String ORDERED_CUSTOMERS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealer\\src\\main\\resources\\files\\output\\ordered-customers.json";
    private final static String TOYOTA_CARS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealer\\src\\main\\resources\\files\\output\\toyota-cars.json";
    private final static String LOCAL_SUPPLIERS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealer\\src\\main\\resources\\files\\output\\local-suppliers.json";
    private final static String CARS_AND_PARTS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealer\\src\\main\\resources\\files\\output\\cars-and-parts.json";
    private final static String CUSTOMERS_TOTAL_SALES_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealer\\src\\main\\resources\\files\\output\\customers-total-sales.json";
    private final static String SALES_WITH_DISCOUNTS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\CarDealer\\src\\main\\resources\\files\\output\\sales-discounts.json";

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public CarDealerController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService, FileUtil fileUtil, Gson gson) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedSuppliers();
//        this.seedParts();
//        this.seedCustomers();
//        this.seedCars();
//        this.saleService.seedSales();

//        this.orderedCustomers();
//        this.toyotaCars();
//        this.localSuppliers();
//        this.carsAndParts();
//        this.customersWithSales();
        this.salesWithDiscounts();
    }

    private void seedSuppliers() throws IOException {
        String suppliersFileContent = this.fileUtil.getFileContent(SUPPLIER_FILE_PATH);
        SupplierSeedDto[] supplierSeedDtos = this.gson.fromJson(suppliersFileContent, SupplierSeedDto[].class);

        this.supplierService.seedSuppliers(supplierSeedDtos);
    }

    private void seedParts() throws IOException {
        String partsFileContent = this.fileUtil.getFileContent(PART_FILE_PATH);
        PartSeedDto[] partSeedDtos = this.gson.fromJson(partsFileContent, PartSeedDto[].class);

        this.partService.seedParts(partSeedDtos);
    }

    private void seedCustomers() throws IOException {
        String customersFileContent = this.fileUtil.getFileContent(CUSTOMER_FILE_PATH);
        CustomerSeedDto[] customerSeedDtos = this.gson.fromJson(customersFileContent, CustomerSeedDto[].class);

        this.customerService.seedCustomers(customerSeedDtos);
    }

    private void seedCars() throws IOException {
        String carsFileContent = this.fileUtil.getFileContent(CAR_FILE_PATH);
        CarSeedDto[] carSeedDtos = this.gson.fromJson(carsFileContent, CarSeedDto[].class);

        this.carService.seedCars(carSeedDtos);
    }

    private void orderedCustomers() throws IOException {
        OrderedCustomersDto[] orderedCustomersDtos = this.customerService.getOrderedCustomers();
        String orderedCustomersJson = this.gson.toJson(orderedCustomersDtos);

        this.fileUtil.writeFile(ORDERED_CUSTOMERS_FILE_PATH, orderedCustomersJson);
    }

    private void toyotaCars() throws IOException {
        ToyotaCarsDto[] toyotaCarsDtos = this.carService.getToyotaCars();
        String toyotaCarsJson = this.gson.toJson(toyotaCarsDtos);

        this.fileUtil.writeFile(TOYOTA_CARS_FILE_PATH, toyotaCarsJson);
    }

    private void localSuppliers() throws IOException {
        List<LocalSuppliersDto> localSuppliersDtos = this.supplierService.getLocalSuppliers();
        String localSuppliersJson = this.gson.toJson(localSuppliersDtos);

        this.fileUtil.writeFile(LOCAL_SUPPLIERS_FILE_PATH, localSuppliersJson);
    }

    private void carsAndParts() throws IOException {
        CarsAndPartsDto[] carsAndPartsDtos = this.carService.getCarsAndParts();
        String carsAndPartsJson = this.gson.toJson(carsAndPartsDtos);

        this.fileUtil.writeFile(CARS_AND_PARTS_FILE_PATH, carsAndPartsJson);
    }

    private void customersWithSales() throws IOException {
        List<CustomerWithSalesDto> customerWithSalesDtos = this.customerService.getCustomerWithPurchasesDto();
        String customerWithPurchasesJson = this.gson.toJson(customerWithSalesDtos);

        this.fileUtil.writeFile(CUSTOMERS_TOTAL_SALES_FILE_PATH, customerWithPurchasesJson);
    }

    private void salesWithDiscounts() throws IOException {
        List<SalesWithDiscountDto> salesWithDiscountDtos = this.saleService.getSalesWithDiscounts();
        String salesWithDiscountJson = this.gson.toJson(salesWithDiscountDtos);

        this.fileUtil.writeFile(SALES_WITH_DISCOUNTS_FILE_PATH, salesWithDiscountJson);
    }
}
