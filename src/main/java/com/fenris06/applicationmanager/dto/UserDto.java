package com.fenris06.applicationmanager.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.ArrayList;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private List<RoleDto> roles = new ArrayList<>();
}
