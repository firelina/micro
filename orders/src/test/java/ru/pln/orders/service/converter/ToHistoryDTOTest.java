package ru.pln.orders.service.converter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.pln.orders.domains.History;
import ru.pln.orders.exception.APIException;
import ru.pln.orders.models.HistoryDTO;
import ru.pln.orders.models.OrderDTO;
import ru.pln.orders.service.data.HistoryBuilder;
import ru.pln.orders.service.data.OrderBuilder;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static ru.pln.orders.messages.MessageException.NULL_HISTORY;
import static ru.pln.orders.messages.MessageException.NULL_ORDER;

@SpringBootTest
class ToHistoryDTOTest {
    @Autowired
    private ToDTO<HistoryDTO> toDTO;
    @Test
    public void whenCorrectOrder_thenCorrectHistory(){
        HistoryDTO dto = toDTO.toDto(OrderBuilder.order().withNumber(1111).build());
        assertAll(
                () -> assertThat(dto).isNotNull(),
                () -> assertThat(dto.getDates()).isEqualTo(new ArrayList<>()),
                () -> assertThat(dto.getOrderNumber()).isEqualTo(1111)
        );
    }
    @Test
    public void whenHasHistoryCollection_thenCorrectHistory(){
        HistoryDTO dto = toDTO.toDto(OrderBuilder.order().withNumber(1111).withHistories(Collections.singleton(new History())).build());
        assertAll(
                () -> assertThat(dto).isNotNull(),
                () -> assertThat(dto.getOrderNumber()).isEqualTo(1111),
                () -> assertThat(dto.getDates()).isNotNull(),
                () -> assertThat(dto.getDates()).hasSize(1)
        );
    }
    @Test
    public void whenHasHistoryCollectionIsNull_thenCorrectHistory(){
        HistoryDTO dto = toDTO.toDto(OrderBuilder.order().withNumber(1111).withHistories(null).build());
        assertAll(
                () -> assertThat(dto).isNotNull(),
                () -> assertThat(dto.getOrderNumber()).isEqualTo(1111),
                () -> assertThat(dto.getDates()).isNotNull(),
                () -> assertThat(dto.getDates()).isEqualTo(new ArrayList<>())
        );
    }
    @Test
    public void whenNullHistory_thenThrowsAPIException(){
        assertAll(
                () -> {
                    APIException exception = assertThrows(APIException.class, () -> toDTO.toDto(null));
                    assertThat(exception.getMessage()).isEqualTo(NULL_HISTORY);
                }
        );
    }
}