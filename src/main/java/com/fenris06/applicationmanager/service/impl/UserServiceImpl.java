package com.fenris06.applicationmanager.service.impl;

import com.fenris06.applicationmanager.dto.UserDto;
import com.fenris06.applicationmanager.exception.NotFoundException;
import com.fenris06.applicationmanager.mapper.UserMapper;
import com.fenris06.applicationmanager.model.Role;
import com.fenris06.applicationmanager.model.User;
import com.fenris06.applicationmanager.repository.RoleRepository;
import com.fenris06.applicationmanager.repository.UserRepository;
import com.fenris06.applicationmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Value("${user.role:ROLE_OPERATOR}")
    private String updateRole;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getUsers(Integer from, Integer size) {
        PageRequest pageRequest = PageRequest.of(from, size);
        log.debug("UserServiceImpl getusers from {}, size {}", from, size);
        return userRepository.findAllWithRoles(pageRequest).stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserDto> updateUsersRole(Set<Long> ids) {
        Role role = getRole();
        List<User> users = getUpdateUsers(ids);
        setRole(users, role);
        log.debug("UserServiceImpl updateUsersRole ids {}", ids);
        return userRepository.saveAll(users).stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    private List<User> getUpdateUsers(Set<Long> ids) {
        return userRepository.findByIdInAndRoles_NameNot(ids, updateRole);
    }

    private Role getRole() {
        return roleRepository.findByName(updateRole)
                .orElseThrow(() -> new NotFoundException(String.format("Role %s not found", updateRole)));
    }

    private void setRole(List<User> users, Role role) {
        users.forEach(user -> user.getRoles().add(role));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findAuthorizationUser(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }
}
