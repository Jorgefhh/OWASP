package com.demo.owasp.dto;

public record RespuestaLoginDto(
    String email,
    String token
) {

}