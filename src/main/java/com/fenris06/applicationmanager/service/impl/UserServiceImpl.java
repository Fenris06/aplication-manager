package com.fenris06.applicationmanager.service.impl;

import com.fenris06.applicationmanager.dto.UserDto;
import com.fenris06.applicationmanager.mapper.UserMapper;
import com.fenris06.applicationmanager.repository.UserRepository;
import com.fenris06.applicationmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getUsers(Integer from, Integer size) {
        PageRequest pageRequest = PageRequest.of(from / size, size);
        return userRepository.findAll(pageRequest).stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}
