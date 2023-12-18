package ru.pln.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.pln.users.repository.RoleRepository;
import ru.pln.users.repository.UserRepository;
import ru.pln.users.service.converter.ToRole;
import ru.pln.users.service.converter.ToRoleDTO;
import ru.pln.users.service.converter.ToUser;
import ru.pln.users.service.converter.ToUserDTO;

@Configuration
@EnableAspectJAutoProxy
public class UserConfig {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public UserConfig(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Bean
    public ToRole toRole() {return new ToRole();}
    @Bean
    public ToRoleDTO toRoleDTO() {return new ToRoleDTO();}
    @Bean
    public ToUserDTO toUserDTO() {return new ToUserDTO();}
    @Bean
    public ToUser toUser() {return new ToUser();}
    @Bean
    public UserService userService() {return new UserService(userRepository, roleRepository, toUserDTO(), toUser());}
    @Bean
    public RoleService roleService() {return new RoleService(toRoleDTO(), toRole(), roleRepository);}
}
