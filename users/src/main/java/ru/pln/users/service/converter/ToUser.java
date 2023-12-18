package ru.pln.users.service.converter;

import ru.pln.users.domains.Role;
import ru.pln.users.domains.User;
import ru.pln.users.models.UserDTO;

public class ToUser {

    public User toDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        Role role = new Role();
        role.setId(userDTO.getRoleId());
        user.setRole(role);
        return user;
    }
}
