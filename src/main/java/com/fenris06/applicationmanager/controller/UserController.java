package com.fenris06.applicationmanager.controller;

import com.fenris06.applicationmanager.dto.UserDto;
import com.fenris06.applicationmanager.service.UserService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {
private final UserService userService;
    @GetMapping
    public List<UserDto> getUsers(@RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                                  @RequestParam(name = "size", required = false, defaultValue = "10") @Min(1) Integer size) {
        return userService.getUsers(from, size);
    }


}
