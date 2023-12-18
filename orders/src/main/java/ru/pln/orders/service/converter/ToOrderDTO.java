package ru.pln.orders.service.converter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.pln.orders.domains.Order;
import ru.pln.orders.exception.APIException;
import ru.pln.orders.models.OrderDTO;
import ru.pln.orders.models.ProductDTO;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.pln.orders.messages.MessageException.NULL_HISTORY;
import static ru.pln.orders.messages.MessageException.NULL_ORDER;
import static ru.pln.orders.util.Checker.checkObject;

public class ToOrderDTO implements ToDTO<OrderDTO> {
    @Override
    public OrderDTO toDto(Order order) {
        checkObject(order, NULL_ORDER);
        return OrderDTO.builder()
                .id(order.getId())
                .creationOrder(order.getCreationOrder())
                .number(order.getNumber())
                .products(Objects.nonNull(order.getProductOrders()) ?
                        order.getProductOrders().stream()
                        .map(p -> ProductDTO.builder()
                                .id(p.getId())
                                .amount(p.getAmount())
                                .productId(p.getProductId())
                                .build())
                        .collect(Collectors.toList()) :
                        Collections.emptyList()
        ).build();
    }

}
