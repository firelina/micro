package ru.pln.products.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.pln.products.domains.Product;
import ru.pln.products.models.ProductDTO;
import ru.pln.products.search.ProductSearch;

import java.util.List;
import static ru.pln.products.util.Checker.checkString;

public class ProductDTOValidateService implements IProductDTOService {
    private final IProductDTOService productDTOService;

    public ProductDTOValidateService(IProductDTOService productDTOService) {
        this.productDTOService = productDTOService;
    }

    @Override
    public ProductDTO save(ProductDTO product) {
        checkString(product.getTitle());
        return productDTOService.save(product);
    }

    @Override
    public ProductDTO update(ProductDTO product) {
        checkString(product.getTitle());
        return productDTOService.update(product);
    }

    @Override
    public boolean delete(Integer id) {
        return productDTOService.delete(id);
    }

    @Override
    public ProductDTO getById(Integer id) {
        return productDTOService.getById(id);
    }

    @Override
    public List<ProductDTO> findAll() {
        return productDTOService.findAll();
    }

    @Override
    public Page<ProductDTO> search(ProductSearch search, PageRequest pageRequest) {
        return productDTOService.search(search, pageRequest);
    }


}
