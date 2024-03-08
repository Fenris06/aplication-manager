package com.fenris06.applicationmanager.dto;

import com.fenris06.applicationmanager.model.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public class UserResponseAuthorizationDto {
    private Long id;
    private Set<RoleDto> roles = new HashSet<>();
}
