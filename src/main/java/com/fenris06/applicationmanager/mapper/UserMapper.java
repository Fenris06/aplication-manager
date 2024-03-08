package com.fenris06.applicationmanager.mapper;

import com.fenris06.applicationmanager.dto.RoleDto;
import com.fenris06.applicationmanager.dto.UserDto;
import com.fenris06.applicationmanager.model.User;

import java.util.List;

import java.util.stream.Collectors;

public class UserMapper {
   public static UserDto toDto (User user) {
       UserDto userDto = new UserDto();
       userDto.setId(user.getId());
       userDto.setUsername(user.getUsername());
       userDto.setEmail(user.getEmail());

       List<RoleDto> roleDtos = user.getRoles()
               .stream()
               .map(RoleMapper::toDto)
               .collect(Collectors.toList());
       userDto.setRoles(roleDtos);
       return userDto;
   }
}
