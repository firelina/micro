package ru.pln.users.service;

import ru.pln.users.models.DTO;

import java.util.List;

public interface ICommonService<D extends DTO> {
    D save(D role);
    D update(D role);
    boolean delete(Integer id);
    D getById(Integer id);
    List<D> findAll();
}
