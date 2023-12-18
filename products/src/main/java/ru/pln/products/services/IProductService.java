package ru.pln.products.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.pln.products.domains.Product;
import ru.pln.products.search.ProductSearch;

import java.util.List;

public interface IProductService {
    Product save(Product product);
    Product update(Product product);
    boolean delete(Integer id);
    Product getById(Integer id);
    List<Product> findAll();
    Page<Product> search(ProductSearch search, PageRequest pageRequest);
}
