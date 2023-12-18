package ru.pln.orders.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.pln.orders.domains.Order;
import ru.pln.orders.search.OrderSearch;

import java.util.List;

public interface IOrderDTOService<T> {
    T save(T order);
    T update(T order);
    boolean delete(Integer id);
    T getById(Integer id);
    List<T> findAll();
    Page<T> search(OrderSearch search, PageRequest pageRequest);
}
