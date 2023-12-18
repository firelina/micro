package ru.pln.orders.service.restexchange;

import ru.pln.orders.models.ProductDTO;

import java.util.Collection;
import java.util.List;

public interface IRestExchange {
    List<ProductDTO> exchange(Collection<Integer> ids);
}
