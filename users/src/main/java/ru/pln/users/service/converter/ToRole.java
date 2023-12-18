package ru.pln.users.service.converter;

import ru.pln.users.domains.Role;
import ru.pln.users.domains.User;
import ru.pln.users.models.RoleDTO;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ToRole {
    public Role toDTO(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setTitle(roleDTO.getTitle());
        return role;
    }
}
