package ru.pln.orders.service.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.CreationTimestamp;
import ru.pln.orders.domains.History;
import ru.pln.orders.domains.Order;
import ru.pln.orders.domains.ProductOrder;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor(staticName = "order")
@With
public class OrderBuilder implements TestDataBuilder<Order> {
    private Integer id = 1;
    private Integer number = 1;
    private Set<ProductOrder> productOrders = new HashSet<>();
    private Date creationOrder = new Date();
    private Set<History> histories = new HashSet<>();
    @Override
    public Order build() {
        final Order order = new Order();
        order.setId(id);
        order.setNumber(number);
        order.setCreationOrder(creationOrder);
        order.setProductOrders(productOrders);
        order.setHistories(histories);
        return order;
    }
}
