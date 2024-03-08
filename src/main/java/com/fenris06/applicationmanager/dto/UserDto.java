package com.fenris06.applicationmanager.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private List<RoleDto> roles = new ArrayList<>();
}
