package ru.pln.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pln.products.domains.Product;
import ru.pln.products.models.ProductDTO;
import ru.pln.products.search.ProductSearch;
import ru.pln.products.services.IProductDTOService;
import ru.pln.products.services.ProductService;

import java.util.List;

import static ru.pln.products.util.PageRequestUtil.createPageRequest;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductDTOService productService;
    @Autowired
    public ProductController(@Qualifier("validatorService") IProductDTOService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO product){
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/product")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product){
        return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return ResponseEntity.ok(productService.delete(id));
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getById(id));
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }
    @PostMapping("/search")
    public ResponseEntity<Page<ProductDTO>> search(@RequestBody ProductSearch search){
        return ResponseEntity.ok(
                productService.search(
                        search,
                        createPageRequest(search)
                )
        );
    }
}
