package ru.pln.users.service.converter;

import ru.pln.users.domains.User;

public interface IToDTO<T> {
    T toDTO(User user);
}
