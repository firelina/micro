package ru.pln.users.service.converter;

import ru.pln.users.domains.User;
import ru.pln.users.models.UserDTO;

import java.util.Objects;

public class ToUserDTO implements IToDTO<UserDTO> {

    @Override
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .roleTitle(Objects.nonNull(user.getRole())? user.getRole().getTitle() : "")
                .roleId(Objects.nonNull(user.getRole())? user.getRole().getId(): 0)
                .password(user.getPassword()).build();
    }
}
