package ru.pln.orders.search;

import lombok.Data;

@Data
public class OrderSearch {
    private Integer orderNumber;
    private Integer pageNumber;
    private Integer pageSize;
    private String sortColumn;
    private String sortDirection;
}
