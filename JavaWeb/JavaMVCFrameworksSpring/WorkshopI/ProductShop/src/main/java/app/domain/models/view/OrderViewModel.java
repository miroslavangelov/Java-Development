package app.domain.models.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderViewModel {
    private String id;
    private String productImageUrl;
    private String customerName;
    private BigDecimal totalPrice;
    private String orderDate;

    public OrderViewModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime localDate = LocalDateTime.parse(orderDate, formatter);

        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDate);
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
