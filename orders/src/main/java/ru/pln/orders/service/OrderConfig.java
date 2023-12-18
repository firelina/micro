package ru.pln.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.pln.orders.models.HistoryDTO;
import ru.pln.orders.models.OrderDTO;
import ru.pln.orders.repository.OrderRepository;
import ru.pln.orders.repository.ProductOrderRepository;
import ru.pln.orders.service.converter.ToDTO;
import ru.pln.orders.service.converter.ToHistoryDTO;
import ru.pln.orders.service.converter.ToOrder;
import ru.pln.orders.service.converter.ToOrderDTO;
import ru.pln.orders.service.restexchange.IRestExchange;

@Configuration
@EnableAspectJAutoProxy
public class OrderConfig {
    private final OrderRepository orderRepository;
    private final ProductOrderRepository productOrderRepository;
    private final IRestExchange restExchange;
    @Autowired
    public OrderConfig(OrderRepository orderRepository, ProductOrderRepository productOrderRepository, @Qualifier("feignExchange") IRestExchange restExchange) {
        this.orderRepository = orderRepository;
        this.productOrderRepository = productOrderRepository;
        this.restExchange = restExchange;
    }

    @Bean
    public ToDTO<OrderDTO> toOrderDTO(){
        return new ToOrderDTO();
    }
    @Bean
    public ToDTO<HistoryDTO> toHistoryDTO(){
        return new ToHistoryDTO();
    }
    @Bean
    public ToOrder toOrder(){
        return new ToOrder();
    }
    @Bean
    public IOrderDTOService<OrderDTO> orderServiceJpa(){
        return new OrderService(orderRepository, productOrderRepository, toOrder(), toOrderDTO());
    }
    @Bean
    public IOrderDTOService<OrderDTO> orderServiceValidator(){
        return new ValidatorOrderService(orderServiceJpa(), restExchange);
    }
}
