package ru.pln.orders.service.converter;

import ru.pln.orders.domains.Order;

public interface ToDTO<T> {
    T toDto(Order order);
}
