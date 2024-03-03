package com.fenris06.applicationmanager.service;


import com.fenris06.applicationmanager.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers(Integer from, Integer size);
}
