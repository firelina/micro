package ru.pln.users.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO implements DTO {

    private Integer id;
    private Integer roleId;
    private String roleTitle;
    private String name;
    private String password;
}
