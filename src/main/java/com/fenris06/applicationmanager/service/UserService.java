package com.fenris06.applicationmanager.service;


import com.fenris06.applicationmanager.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    List<UserDto> getUsers(Integer from, Integer size);

    List<UserDto> updateUsersRole(Set<Long> ids);
}
