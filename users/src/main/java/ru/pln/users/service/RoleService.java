package ru.pln.users.service;


import org.springframework.transaction.annotation.Transactional;
import ru.pln.users.domains.Role;
import ru.pln.users.exception.APIException;
import ru.pln.users.models.RoleDTO;
import ru.pln.users.repository.RoleRepository;
import ru.pln.users.service.converter.ToRole;
import ru.pln.users.service.converter.ToRoleDTO;

import java.util.List;
import java.util.stream.Collectors;

import static ru.pln.users.messages.MessageException.NOT_FOUND;

public class RoleService implements ICommonService<RoleDTO> {
    private final ToRoleDTO toRoleDTO;
    private final ToRole toRole;
    private final RoleRepository roleRepository;

    public RoleService(ToRoleDTO toRoleDTO, ToRole toRole, RoleRepository roleRepository) {
        this.toRoleDTO = toRoleDTO;
        this.toRole = toRole;
               this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public RoleDTO save(RoleDTO role) {
        Role persist = roleRepository.save(toRole.toDTO(role));
        return toRoleDTO.toDTO(persist);
    }

    @Override
    @Transactional
    public RoleDTO update(RoleDTO role) {
        Role persist = roleRepository.findById(role.getId()).orElseThrow(() -> new APIException(NOT_FOUND));
        persist.setTitle(role.getTitle());
//        persist.getUsers().addAll(roleRepository.saveAll(role.getUsers().stream().map(u -> toUser.toDTO(u)).collect(Collectors.toList())));
        return null;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        getById(id);
        roleRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public RoleDTO getById(Integer id) {
        return toRoleDTO.toDTO(roleRepository.findById(id).orElseThrow(() -> new APIException(NOT_FOUND)));
    }

    @Override
    @Transactional
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream().map(toRoleDTO::toDTO).collect(Collectors.toList());
    }
}
