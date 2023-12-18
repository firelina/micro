package ru.pln.users.service;

import ru.pln.users.models.RoleDTO;

import java.util.List;

import static ru.pln.users.messages.MessageException.NULL_ID;
import static ru.pln.users.messages.MessageException.NULL_TITLE;
import static ru.pln.users.util.Checker.checkNumber;
import static ru.pln.users.util.Checker.checkString;

public class ValidatorRoleService implements ICommonService<RoleDTO> {
    public final RoleService roleService;

    public ValidatorRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDTO save(RoleDTO role) {
        checkNumber(role.getId(), NULL_ID);
        checkString(role.getTitle(), NULL_TITLE);
        return roleService.save(role);
    }

    @Override
    public RoleDTO update(RoleDTO role) {
        checkNumber(role.getId(), NULL_ID);
        checkString(role.getTitle(), NULL_TITLE);
        return roleService.update(role);
    }

    @Override
    public boolean delete(Integer id) {
        return roleService.delete(id);
    }

    @Override
    public RoleDTO getById(Integer id) {
        return roleService.getById(id);
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleService.findAll();
    }
}
