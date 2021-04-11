package com.socialmedia.demo.model.Response;

// This class will send back the JWT token
//if the username and password are correct
public class LoginResponse {

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }

    private String JWT;

    public LoginResponse(String JWT) {
        this.JWT = JWT;
    }
}
