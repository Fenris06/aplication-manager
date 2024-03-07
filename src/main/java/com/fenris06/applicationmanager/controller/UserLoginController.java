package com.fenris06.applicationmanager.controller;

import com.fenris06.applicationmanager.dto.UserAuthorizationDto;
import com.fenris06.applicationmanager.dto.UserDto;
import com.fenris06.applicationmanager.model.User;
import com.fenris06.applicationmanager.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Validated
public class UserLoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid UserAuthorizationDto userAuthorizationDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthorizationDto.getUsername(), userAuthorizationDto.getPassword()));
        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        UserDto responseDto = new UserDto(); // TODO подумать что возвращать теле
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .body(responseDto);
    }
}
