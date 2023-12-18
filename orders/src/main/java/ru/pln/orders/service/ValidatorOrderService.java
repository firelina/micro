package ru.pln.orders.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.pln.orders.models.OrderDTO;
import ru.pln.orders.models.ProductDTO;
import ru.pln.orders.search.OrderSearch;
import ru.pln.orders.service.restexchange.IRestExchange;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;
import static ru.pln.orders.messages.MessageException.NULL_ORDER_NUMBER;
import static ru.pln.orders.messages.MessageException.PRODUCT_NOT_FOUND;
import static ru.pln.orders.util.Checker.checkNumber;

public class ValidatorOrderService implements IOrderDTOService<OrderDTO> {
    private final IOrderDTOService<OrderDTO> service;
    private final IRestExchange restExchange;

    public ValidatorOrderService(IOrderDTOService<OrderDTO> service, IRestExchange restExchange) {
        this.service = service;
        this.restExchange = restExchange;
    }

    @Override
    public OrderDTO save(OrderDTO order) {
        checkNumber(order.getNumber(), NULL_ORDER_NUMBER);
        return service.save(order);
    }

    @Override
    public OrderDTO update(OrderDTO order) {
        checkNumber(order.getNumber(), NULL_ORDER_NUMBER);
        return service.update(order);
    }

    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public OrderDTO getById(Integer id) {
        OrderDTO orderDTO = service.getById(id);
        addTitleToProduct(orderDTO);
        return orderDTO;
    }

    private void addTitleToProduct(OrderDTO orderDTO) {
        List<ProductDTO> productDTOS = restExchange.exchange(orderDTO.getProducts().stream().map(ProductDTO::getProductId).collect(Collectors.toSet()));
        Map<Integer, ProductDTO> productsFromExchange = productDTOS.stream().collect(toMap(ProductDTO::getProductId, Function.identity()));
        orderDTO.getProducts().forEach(p -> {
            final String title = productsFromExchange.getOrDefault(p.getProductId(), ProductDTO.builder().title(PRODUCT_NOT_FOUND).build()).getTitle();
            p.setTitle(title);
        });
    }

    @Override
    public List<OrderDTO> findAll() {
        List<OrderDTO> orderDTOList = service.findAll();
        orderDTOList.forEach(this::addTitleToProduct);
        return orderDTOList;
    }
    @Override
    public Page<OrderDTO> search(OrderSearch search, PageRequest pageRequest) {
        Page<OrderDTO> ordersPage = service.search(search, pageRequest);
        ordersPage.getContent().forEach(this::addTitleToProduct);
        return ordersPage;
    }
}
