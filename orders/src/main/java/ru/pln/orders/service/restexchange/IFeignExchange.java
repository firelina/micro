package ru.pln.orders.service.restexchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import ru.pln.orders.models.ProductDTO;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "product-service", fallback = IFeignExchange.ProductsFallBack.class)
@Primary
public interface IFeignExchange {
    @GetMapping("/products/products")
    ResponseEntity<List<ProductDTO>> exchange();

    @Component
    class ProductsFallBack implements IFeignExchange{
        @Override
        public ResponseEntity<List<ProductDTO>> exchange() {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }
}
