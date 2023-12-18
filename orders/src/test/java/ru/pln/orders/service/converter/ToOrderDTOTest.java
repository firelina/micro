package ru.pln.orders.service.converter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.pln.orders.exception.APIException;
import ru.pln.orders.models.OrderDTO;
import ru.pln.orders.service.data.OrderBuilder;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.pln.orders.messages.MessageException.NULL_ORDER;

@SpringBootTest
class ToOrderDTOTest {
    @Autowired
    private ToDTO<OrderDTO> toDTO;
    @Test
    public void whenCorrectOrder_thenCorrectOrderDTO(){
        OrderDTO dto = toDTO.toDto(OrderBuilder.order().build());
        assertAll(
                () -> assertThat(dto).isNotNull(),
                () -> assertThat(dto.getId()).isEqualTo(1),
                () -> assertThat(dto.getNumber()).isEqualTo(1),
                () -> assertThat(dto.getCreationOrder()).isBefore(new Date()),
                () -> assertThat(dto.getProducts()).hasSize(0)
        );
    }
    @Test
    public void whenNullProductsOrder_thenCorrectOrderDTO(){
        OrderDTO dto = toDTO.toDto(OrderBuilder.order().withProductOrders(null).build());
        assertAll(
                () -> assertThat(dto).isNotNull(),
                () -> assertThat(dto.getId()).isEqualTo(1),
                () -> assertThat(dto.getNumber()).isEqualTo(1),
                () -> assertThat(dto.getCreationOrder()).isBefore(new Date()),
                () -> assertThat(dto.getProducts()).hasSize(0)
        );
    }
    @Test
    public void whenNullOrder_thenThrowsAPIException(){
        assertAll(
                () -> {
                    APIException exception = assertThrows(APIException.class, () -> toDTO.toDto(null));
                    assertThat(exception.getMessage()).isEqualTo(NULL_ORDER);
                }
        );
    }
}