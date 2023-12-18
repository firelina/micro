package ru.pln.products.search;

import lombok.Data;

@Data
public class ProductSearch {
    private String title;
    private Integer pageNumber;
    private Integer pageSize;
    private String sortColumn;
    private String sortDirection;
}
