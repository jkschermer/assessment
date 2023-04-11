package com.example.assessment.response;

public class AuthenticationResponse {

    private final String status;
    private final String jwt;

    public AuthenticationResponse(String status, String jwt) {
        this.status = status;
        this.jwt = jwt;
    }

    public String getStatus() {
        return status;
    }

    public String getJwt() {
        return jwt;
    }
}
