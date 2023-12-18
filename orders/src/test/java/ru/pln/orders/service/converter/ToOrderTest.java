package ru.pln.orders.service.converter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.pln.orders.domains.Order;
import ru.pln.orders.domains.ProductOrder;
import ru.pln.orders.exception.APIException;
import ru.pln.orders.models.OrderDTO;
import ru.pln.orders.models.ProductDTO;
import ru.pln.orders.service.data.OrderDTOBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static ru.pln.orders.messages.MessageException.NULL_ORDER;
import static ru.pln.orders.messages.MessageException.NULL_ORDER_DTO;

@SpringBootTest
class ToOrderTest {
    @Autowired
    private ToOrder toOrder;
    @Test
    public void whenCorrectOrderDTO_thanCorrectOrder(){
        Order order = toOrder.toEntity(OrderDTOBuilder.OrderDTO().build());
        assertAll(
                () -> assertThat(order).isNotNull(),
                () -> assertThat(order.getId()).isEqualTo(1),
                () -> assertThat(order.getNumber()).isEqualTo(1),
                () -> assertThat(order.getProductOrders()).isNotNull(),
                () -> assertThat(order.getProductOrders()).hasSize(0)
        );
    }
    @Test
    public void whenHasProductsDTO_thanCorrectOrder(){
        Order order = toOrder.toEntity(OrderDTOBuilder.OrderDTO()
                .withProducts(Collections.singletonList(ProductDTO.builder().build()))
                .build());
        assertAll(
                () -> assertThat(order).isNotNull(),
                () -> assertThat(order.getId()).isEqualTo(1),
                () -> assertThat(order.getNumber()).isEqualTo(1),
                () -> assertThat(order.getProductOrders()).isNotNull(),
                () -> assertThat(order.getProductOrders()).hasSize(1)
        );
    }
    @Test
    public void whenProductOrdersNull_thanCorrectOrder(){
        Order order = toOrder.toEntity(OrderDTOBuilder.OrderDTO()
                .withProducts(null)
                .build());
        assertAll(
                () -> assertThat(order).isNotNull(),
                () -> assertThat(order.getId()).isEqualTo(1),
                () -> assertThat(order.getNumber()).isEqualTo(1),
                () -> assertThat(order.getProductOrders()).isNotNull(),
                () -> assertThat(order.getProductOrders()).hasSize(0)
        );
    }
    @Test
    public void whenNullProductOrders_thenThrowsAPIException(){
        assertAll(
                () -> {
                    APIException exception = assertThrows(APIException.class, () -> toOrder.toEntity(null));
                    assertThat(exception.getMessage()).isEqualTo(NULL_ORDER_DTO);
                }
        );
    }
}