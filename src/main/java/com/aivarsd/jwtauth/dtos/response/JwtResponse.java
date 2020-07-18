package com.aivarsd.jwtauth.dtos.response;

import com.aivarsd.jwtauth.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private User user;
    private String token;
    private String type = "Bearer";
    private String message;

    public JwtResponse(String accessToken, User user, String message) {
        this.token = accessToken;
        this.user = user;
        this.message = message;
    }
}