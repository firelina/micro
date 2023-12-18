package ru.pln.orders.models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderDTO implements Serializable {
    private Integer id;
    private Date creationOrder;
    private Integer number;
    private List<ProductDTO> products;
}
