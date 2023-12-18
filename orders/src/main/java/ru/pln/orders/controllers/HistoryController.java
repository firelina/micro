package ru.pln.orders.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pln.orders.domains.History;
import ru.pln.orders.models.HistoryDTO;
import ru.pln.orders.models.OrderDTO;
import ru.pln.orders.service.IOrderDTOService;

@RestController
@RequestMapping("/histories")
public class HistoryController {

    private IOrderDTOService<HistoryDTO> service;
    @GetMapping("/history/{id}")
    ResponseEntity<HistoryDTO> getById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getById(id));
    }
}
