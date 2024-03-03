package com.fenris06.applicationmanager.mapper;

import com.fenris06.applicationmanager.dto.RoleDto;
import com.fenris06.applicationmanager.model.Role;

public class RoleMapper {
    public static RoleDto toDto (Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }
}
