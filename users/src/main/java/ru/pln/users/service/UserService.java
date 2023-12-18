package ru.pln.users.service;


import org.springframework.transaction.annotation.Transactional;
import ru.pln.users.domains.Role;
import ru.pln.users.domains.User;
import ru.pln.users.exception.APIException;
import ru.pln.users.models.UserDTO;
import ru.pln.users.repository.RoleRepository;
import ru.pln.users.repository.UserRepository;
import ru.pln.users.service.converter.ToUser;
import ru.pln.users.service.converter.ToUserDTO;

import java.util.List;
import java.util.stream.Collectors;

import static ru.pln.users.messages.MessageException.NOT_FOUND;

public class UserService implements ICommonService<UserDTO> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ToUserDTO toUserDTO;
    private final ToUser toUser;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, ToUserDTO toUserDTO, ToUser toUser) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.toUserDTO = toUserDTO;
        this.toUser = toUser;
    }


    @Override
    @Transactional
    public UserDTO save(UserDTO user) {
        User newUser = toUser.toDTO(user);
        newUser.setRole(findRole(user.getRoleId()));
        User persist = userRepository.save(newUser);
        return toUserDTO.toDTO(persist);
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO user) {
        User persist = userRepository.findById(user.getId()).orElseThrow(() -> new APIException(NOT_FOUND));
        persist.setName(user.getName());
        persist.setPassword(user.getPassword());
        persist.setRole(findRole(user.getRoleId()));
        return toUserDTO.toDTO(persist);
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        getById(id);
        userRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public UserDTO getById(Integer id) {
        return toUserDTO.toDTO(userRepository.findById(id).orElseThrow(() -> new APIException(NOT_FOUND)));
    }

    @Override
    @Transactional
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(toUserDTO::toDTO).collect(Collectors.toList());
    }

    private Role findRole(Integer id){
        return roleRepository.findById(id).orElseThrow(() -> new APIException(NOT_FOUND));
    }
}
