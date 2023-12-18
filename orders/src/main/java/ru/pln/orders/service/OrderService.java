package ru.pln.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pln.orders.domains.History;
import ru.pln.orders.domains.Order;
import ru.pln.orders.domains.ProductOrder;
import ru.pln.orders.exception.APIException;
import ru.pln.orders.models.OrderDTO;
import ru.pln.orders.repository.OrderRepository;
import ru.pln.orders.repository.ProductOrderRepository;
import ru.pln.orders.search.OrderSearch;
import ru.pln.orders.service.converter.ToDTO;
import ru.pln.orders.service.converter.ToOrder;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.pln.orders.messages.MessageException.NOT_FOUND;


public class OrderService implements IOrderDTOService<OrderDTO> {
    private final OrderRepository orderRepository;
    private final ProductOrderRepository productOrderRepository;
    private final ToOrder toOrder;
    private final ToDTO<OrderDTO> toOrderDTO;

    public OrderService(OrderRepository orderRepository, ProductOrderRepository productOrderRepository, ToOrder toOrder, ToDTO<OrderDTO> toOrderDTO) {
        this.orderRepository = orderRepository;
        this.productOrderRepository = productOrderRepository;
        this.toOrder = toOrder;
        this.toOrderDTO = toOrderDTO;
    }

    @Override
    @Transactional
    public OrderDTO save(OrderDTO order) {
        List<ProductOrder> productOrders = productOrderRepository.saveAll(
            order.getProducts().stream()
                .map(po -> new ProductOrder(po.getId(), po.getAmount(), po.getProductId()))
                .collect(Collectors.toSet())
        );
        Order persist = orderRepository.save(toOrder.toEntity(order));
        persist.setProductOrders(new HashSet<>(productOrders));
        return toOrderDTO.toDto(persist);
    }

    @Override
    @Transactional
    public OrderDTO update(OrderDTO order) {
        Order persist = orderRepository.findById(order.getId()).orElseThrow(() -> new APIException(NOT_FOUND));
        persist.setNumber(order.getNumber());
        persist.getProductOrders().clear();
        persist.getProductOrders().addAll(productOrderRepository.saveAll(
                order.getProducts().stream()
                        .map(po -> new ProductOrder(po.getId(), po.getAmount(), po.getProductId()))
                        .collect(Collectors.toSet())
        ));
        if(Objects.isNull(persist.getHistories()))
            persist.setHistories(new HashSet<>());
        persist.getHistories().add(new History());
        return toOrderDTO.toDto(persist);
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        getById(id);
        orderRepository.deleteById(id);
        return true;
    }


    @Override
    @Transactional(readOnly = true)
    public OrderDTO getById(Integer id) {
        return toOrderDTO.toDto(orderRepository.findById(id).orElseThrow(() -> new APIException(NOT_FOUND)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream().map(toOrderDTO::toDto).collect(Collectors.toList());
    }
    @Override
    public Page<OrderDTO> search(OrderSearch search, PageRequest pageRequest) {
        Page<Order> productPage = orderRepository.search(search.getOrderNumber(), pageRequest);
        return new PageImpl<>(
                productPage.getContent().stream()
                        .map(toOrderDTO::toDto)
                        .collect(Collectors.toList()),
                pageRequest,
                productPage.getTotalElements()
        );
//        return orderRepository.search(search.getOrderNumber(), pageRequest);
    }
}
