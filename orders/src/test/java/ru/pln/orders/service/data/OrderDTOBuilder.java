package ru.pln.orders.service.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import ru.pln.orders.models.OrderDTO;
import ru.pln.orders.models.ProductDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(staticName = "OrderDTO")
@With
public class OrderDTOBuilder implements TestDataBuilder<OrderDTO>{
    private Integer id = 1;
    private Date creationOrder = new Date();
    private Integer number = 1;
    private List<ProductDTO> products = new ArrayList<>();
    @Override
    public OrderDTO build() {
        final  OrderDTO orderDTO = OrderDTO.builder().id(id).creationOrder(creationOrder).number(number).products(products).build();
        return orderDTO;
    }
}
