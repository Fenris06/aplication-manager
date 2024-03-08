package com.fenris06.applicationmanager.service;

import com.fenris06.applicationmanager.model.User;
import io.jsonwebtoken.Claims;

public interface JwtService {

    String generateToken(User user);

    Claims parseClaims(String token);
}
