package alararestaurant.service;

import alararestaurant.domain.dtos.OrderImportDto;
import alararestaurant.domain.dtos.OrderImportRootDto;
import alararestaurant.domain.dtos.OrderItemImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderItem;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import alararestaurant.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static alararestaurant.constants.Constants.INCORRECT_DATA_MESSAGE;

@Service
public class OrderServiceImpl implements OrderService {
    private final static String ORDERS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\orders.xml";

    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, EmployeeRepository employeeRepository, ItemRepository itemRepository, OrderItemRepository orderItemRepository, ModelMapper modelMapper, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {
        return this.fileUtil.readFile(ORDERS_FILE_PATH);
    }

    @Override
    public String importOrders() throws JAXBException, ParseException, FileNotFoundException {
        StringBuilder result = new StringBuilder();
        OrderImportRootDto orderImportRootDto = this.xmlParser
                .parseXml(OrderImportRootDto.class, ORDERS_FILE_PATH);

        for (OrderImportDto orderImportDto: orderImportRootDto.getOrderImportDtos()) {
            Employee employee = this.employeeRepository.findByName(orderImportDto.getEmployee());

            if (employee == null) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Order order = this.modelMapper.map(orderImportDto, Order.class);
            order.setDateTime(LocalDateTime.parse(orderImportDto.getDateTime(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            order.setEmployee(employee);

            if (!this.validationUtil.isValid(order)) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            List<OrderItem> orderItems = new ArrayList<>();
            boolean hasInvalidItem = false;
            for (OrderItemImportDto orderItemImportDto: orderImportDto.getOrderItemImportRootDto().getOrderItemImportDtos()) {
                Item item = this.itemRepository.findByName(orderItemImportDto.getName());

                if (item == null) {
                    result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    continue;
                }

                OrderItem orderItem = new OrderItem();
                orderItem.setItem(item);
                orderItem.setOrder(order);
                orderItem.setQuantity(orderItemImportDto.getQuantity());

                if (!this.validationUtil.isValid(orderItem)) {
                    hasInvalidItem = true;
                    break;
                }
                orderItems.add(orderItem);
            }

            if (hasInvalidItem) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
            } else {
                order.setOrderItems(orderItems);
                this.orderRepository.saveAndFlush(order);
                this.orderItemRepository.saveAll(orderItems);
                result.append(String.format(
                        "Order for %s on %s added",
                        order.getCustomer(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(order.getDateTime()))
                ).append(System.lineSeparator());
            }
        }

        return result.toString().trim();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        StringBuilder result = new StringBuilder();
        List<Order> orders = this.orderRepository.exportOrders("Burger Flipper");

        for (Order order: orders) {
            result.append(String.format("Name: %s", order.getEmployee().getName())).append(System.lineSeparator())
                    .append("Orders:").append(System.lineSeparator())
                    .append(String.format("  Customer: %s", order.getCustomer())).append(System.lineSeparator())
                    .append("  Items:").append(System.lineSeparator());

            for (OrderItem orderItem: order.getOrderItems()) {
                result.append(String.format("    Name: %s", orderItem.getItem().getName()))
                        .append(System.lineSeparator())
                        .append(String.format("    Price: %.2f", orderItem.getItem().getPrice()))
                        .append(System.lineSeparator())
                        .append(String.format("    Quantity: %d", orderItem.getQuantity()))
                        .append(System.lineSeparator())
                        .append(System.lineSeparator());
            }
        }

        return result.toString().trim();
    }
}
