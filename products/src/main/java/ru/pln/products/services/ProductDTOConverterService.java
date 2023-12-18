package ru.pln.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.pln.products.domains.Product;
import ru.pln.products.models.ProductDTO;
import ru.pln.products.search.ProductSearch;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDTOConverterService implements IProductDTOService{
    private final IProductService productService;

    public ProductDTOConverterService(IProductService productService) {
        this.productService = productService;
    }

    @Override
    public ProductDTO save(ProductDTO product) {
        Product entity = new Product(null, product.getTitle());
        entity = productService.save(entity);
        return ProductDTO.builder().id(entity.getId()).title(entity.getTitle()).build();
    }

    @Override
    public ProductDTO update(ProductDTO product) {
        Product entity = new Product(product.getId(), product.getTitle());
        entity = productService.save(entity);
        return ProductDTO.builder().id(entity.getId()).title(entity.getTitle()).build();
    }

    @Override
    public boolean delete(Integer id) {
        return productService.delete(id);
    }

    @Override
    public ProductDTO getById(Integer id) {
        final Product product = productService.getById(id);
        return ProductDTO.builder().id(product.getId()).title(product.getTitle()).build();
    }

    @Override
    public List<ProductDTO> findAll() {
        return productService.findAll()
                .stream()
                .map(p -> ProductDTO.builder().id(p.getId()).title(p.getTitle()).build())
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDTO> search(ProductSearch search, PageRequest pageRequest) {
        Page<Product> productPage = productService.search(search, pageRequest);
        return new PageImpl<>(
                productPage.getContent().stream()
                .map(p -> ProductDTO.builder().id(p.getId()).title(p.getTitle()).build())
                .collect(Collectors.toList()),
                pageRequest,
                productPage.getTotalElements()
        );
    }
}