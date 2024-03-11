package com.fenris06.applicationmanager.controller;

import com.fenris06.applicationmanager.dto.UserDto;
import com.fenris06.applicationmanager.service.UserService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/person")
    public List<UserDto> getUsers(@RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                                  @RequestParam(name = "size", required = false, defaultValue = "10") @Min(1) Integer size) {
        log.debug("GET /admin/person from {}, size {}", from, size);
        return userService.getUsers(from, size);
    }

    @PatchMapping("/person")
    public List<UserDto> updateUsersRole(@RequestParam(name = "ids", required = false, defaultValue = "") @Size(min = 1, max = 5) Set<Long> ids) {
        log.debug("PATCH /admin/person ids {}", ids);
        return userService.updateUsersRole(ids);
    }
}
