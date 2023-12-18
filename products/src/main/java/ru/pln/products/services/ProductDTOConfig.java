package ru.pln.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDTOConfig {
    private final IProductService productService;
    @Autowired
    public ProductDTOConfig(IProductService productService) {
        this.productService = productService;
    }
    @Bean
    public IProductDTOService converterService(){
        return new ProductDTOConverterService(productService);
    }
    @Bean
    public IProductDTOService validatorService(){
        return new ProductDTOValidateService(converterService());
    }
}
