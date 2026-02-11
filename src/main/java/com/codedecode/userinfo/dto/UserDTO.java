package com.codedecode.userinfo.dto;

public record UserDTO(
         Long userId,
         String userName,
         String userPassword,
         String address,
         String city) {
}
