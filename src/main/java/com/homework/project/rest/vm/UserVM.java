package com.homework.project.rest.vm;

import com.homework.project.domain.UserStatus;
import com.homework.project.domain.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVM {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserStatus status;
    private UserType type;
    private Double hourSalary;
    private Long department;
    private Long position;
}
