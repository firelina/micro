package ru.pln.orders.service.converter;

import ru.pln.orders.domains.Order;
import ru.pln.orders.domains.ProductOrder;
import ru.pln.orders.exception.APIException;
import ru.pln.orders.models.OrderDTO;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.pln.orders.messages.MessageException.*;
import static ru.pln.orders.util.Checker.checkObject;

public class ToOrder {
    public Order toEntity(OrderDTO orderDTO){
        checkObject(orderDTO, NULL_ORDER_DTO);
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setNumber(orderDTO.getNumber());
        order.setProductOrders(Objects.nonNull(orderDTO.getProducts()) ?
                orderDTO.getProducts().stream()
                        .map(p -> new ProductOrder(p.getId(), p.getAmount(), p.getProductId()))
                        .collect(Collectors.toSet()):
                Collections.emptySet());
        return order;
    }
}
