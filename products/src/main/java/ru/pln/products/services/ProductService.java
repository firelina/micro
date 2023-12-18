package ru.pln.products.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pln.products.domains.Product;
import ru.pln.products.exception.APIException;
import ru.pln.products.repos.ProductRepo;
import ru.pln.products.search.ProductSearch;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ru.pln.products.messages.MessageException.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepo productRepo;
    @Override
    @Transactional
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    @Transactional
    public Product update(Product product) {
        Product persistProduct = this.getById(product.getId());
        persistProduct.setTitle(product.getTitle());
        return persistProduct;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        this.getById(id);
        productRepo.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getById(Integer id) {
        Optional<Product> product = productRepo.findById(id);
        Product persist = product.orElseThrow(() -> new APIException(NOT_FOUND));
        return persist;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Page<Product> search(ProductSearch search, PageRequest pageRequest) {
        return productRepo.search(search.getTitle(), pageRequest);
    }
}
