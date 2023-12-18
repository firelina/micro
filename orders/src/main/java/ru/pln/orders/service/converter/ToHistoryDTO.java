package ru.pln.orders.service.converter;

import ru.pln.orders.domains.History;
import ru.pln.orders.domains.Order;
import ru.pln.orders.exception.APIException;
import ru.pln.orders.models.HistoryDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.pln.orders.messages.MessageException.NULL_HISTORY;
import static ru.pln.orders.util.Checker.checkObject;

public class ToHistoryDTO implements ToDTO<HistoryDTO> {
    @Override
    public HistoryDTO toDto(Order order) {
        checkObject(order, NULL_HISTORY);
        return HistoryDTO.builder()
                .orderNumber(order.getNumber())
                .dates(Objects.nonNull(order.getHistories()) ? order.getHistories()
                        .stream()
                        .map(History::getUpdateOrder)
                        .collect(Collectors.toList()) : Collections.emptyList())
                .build();
    }
}
