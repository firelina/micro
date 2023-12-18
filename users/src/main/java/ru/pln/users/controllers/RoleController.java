package ru.pln.users.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pln.users.models.RoleDTO;

import ru.pln.users.service.ICommonService;


import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final ICommonService<RoleDTO> service;

    public RoleController(ICommonService<RoleDTO> service) {
        this.service = service;
    }
    @PostMapping("/role")
    ResponseEntity<RoleDTO> save(@RequestBody RoleDTO role) {
        return ResponseEntity.ok(service.save(role));
    }
    @PutMapping("/role")
    ResponseEntity<RoleDTO> update(@RequestBody RoleDTO role) {
        return ResponseEntity.ok(service.update(role));
    }
    @DeleteMapping("/role/{id}")
    ResponseEntity<Boolean> delete(@PathVariable Integer id) {return ResponseEntity.ok(service.delete(id));}
    @GetMapping("/role/{id}")
    ResponseEntity<RoleDTO> getById(@PathVariable Integer id) {return ResponseEntity.ok(service.getById(id));}
    @GetMapping("/roles")
    ResponseEntity<List<RoleDTO>> findAll() {return ResponseEntity.ok(service.findAll());}
}
