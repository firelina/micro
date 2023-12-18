package ru.pln.products.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.pln.products.domains.Product;
import ru.pln.products.models.ProductDTO;
import ru.pln.products.search.ProductSearch;

import java.util.List;

public interface IProductDTOService {
    ProductDTO save(ProductDTO product);
    ProductDTO update(ProductDTO product);
    boolean delete(Integer id);
    ProductDTO getById(Integer id);
    List<ProductDTO> findAll();
    Page<ProductDTO> search(ProductSearch search, PageRequest pageRequest);
}
