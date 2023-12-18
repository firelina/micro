package ru.pln.users.service.converter;

import ru.pln.users.domains.Role;
import ru.pln.users.domains.User;
import ru.pln.users.models.RoleDTO;

public class ToRoleDTO {
    public RoleDTO toDTO(Role role) {
        return RoleDTO.builder().id(role.getId()).title(role.getTitle()).build();
    }
}
