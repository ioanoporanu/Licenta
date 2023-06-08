package com.example.BlaBlaCar.payload.request;

import lombok.Getter;

import jakarta.validation.constraints.NotBlank;

@Getter
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
