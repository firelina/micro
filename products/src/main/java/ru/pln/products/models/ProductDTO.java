package ru.pln.products.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductDTO {
    private final Integer id;
    private final String title;
}
