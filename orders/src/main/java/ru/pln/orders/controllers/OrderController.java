package ru.pln.orders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pln.orders.models.OrderDTO;
import ru.pln.orders.search.OrderSearch;
import ru.pln.orders.service.IOrderDTOService;

import java.util.List;

import static ru.pln.orders.util.PageRequestUtil.createPageRequest;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final IOrderDTOService<OrderDTO> service;
    @Autowired
    public OrderController(@Qualifier("orderServiceValidator") IOrderDTOService<OrderDTO> service) {
        this.service = service;
    }

    @PostMapping("/order")
    ResponseEntity<OrderDTO> save(@RequestBody OrderDTO product){
        return ResponseEntity.ok(service.save(product));
    }

    @PutMapping("/order")
    ResponseEntity<OrderDTO> update(@RequestBody OrderDTO product){
        return ResponseEntity.ok(service.update(product));
    }

    @DeleteMapping("/order/{id}")
    ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return ResponseEntity.ok(service.delete(id));
    }
    @GetMapping("/order/{id}")
    ResponseEntity<OrderDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping("/order")
    ResponseEntity<List<OrderDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    @PostMapping("/search")
    public ResponseEntity<Page<OrderDTO>> search(@RequestBody OrderSearch search){
        return ResponseEntity.ok(
                service.search(
                        search,
                        createPageRequest(search)
                )
        );
    }
}
