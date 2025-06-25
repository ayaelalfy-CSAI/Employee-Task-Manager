package com.project.AuthService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto {
    private String username;
    private String email;
    private long id;
}
