package ru.pln.users.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pln.users.models.UserDTO;
import ru.pln.users.service.ICommonService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final ICommonService<UserDTO> service;

    public UserController(ICommonService<UserDTO> service) {
        this.service = service;
    }
    @PostMapping("/user")
    ResponseEntity<UserDTO> save(@RequestBody UserDTO user) {
        return ResponseEntity.ok(service.save(user));
    }
    @PutMapping("/user")
    ResponseEntity<UserDTO> update(@RequestBody UserDTO user) {
        return ResponseEntity.ok(service.update(user));
    }
    @DeleteMapping("/user/{id}")
    ResponseEntity<Boolean> delete(@PathVariable Integer id) {return ResponseEntity.ok(service.delete(id));}
    @GetMapping("/user/{id}")
    ResponseEntity<UserDTO> getById(@PathVariable Integer id) {return ResponseEntity.ok(service.getById(id));}
    @GetMapping("/users")
    ResponseEntity<List<UserDTO>> findAll() {return ResponseEntity.ok(service.findAll());}
}
