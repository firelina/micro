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

import static ru.pln.users.messages.MessageException.*;
import static ru.pln.users.util.Checker.checkNumber;
import static ru.pln.users.util.Checker.checkString;

public class ValidatorUserService implements ICommonService<UserDTO> {
    private final UserService userService;

    public ValidatorUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    @Transactional
    public UserDTO save(UserDTO user) {
        checkNumber(user.getId(), NULL_ID);
        checkString(user.getName(), NULL_NAME);
        checkString(user.getPassword(), NULL_PASSWORD);
        return userService.save(user);
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO user) {
        checkNumber(user.getId(), NULL_ID);
        checkString(user.getName(), NULL_NAME);
        checkString(user.getPassword(), NULL_PASSWORD);
        return userService.update(user);
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return userService.delete(id);
    }

    @Override
    @Transactional
    public UserDTO getById(Integer id) {
        return userService.getById(id);
    }

    @Override
    @Transactional
    public List<UserDTO> findAll() {
        return userService.findAll();
    }


}
