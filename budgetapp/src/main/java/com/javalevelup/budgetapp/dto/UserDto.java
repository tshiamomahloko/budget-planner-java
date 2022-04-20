package com.javalevelup.budgetapp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class UserDto {
    private String username;
    private String password;
}
