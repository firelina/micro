package ru.pln.users.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleDTO implements DTO {
    private Integer id;
    private String title;
}
