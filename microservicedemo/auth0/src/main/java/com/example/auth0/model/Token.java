package com.example.auth0.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private Integer expires_in;
    private String scope;
}
