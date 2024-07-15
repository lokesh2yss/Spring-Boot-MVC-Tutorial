package com.codingshuttle.springmvctutorial.springmvctutorial.dto;

import com.codingshuttle.springmvctutorial.springmvctutorial.annotations.EmployeeRoleValidation;
import com.codingshuttle.springmvctutorial.springmvctutorial.annotations.PasswordValidation;
import com.codingshuttle.springmvctutorial.springmvctutorial.annotations.PrimeNumberValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Employee name cannot be blank")
    @Size(min = 3, max = 15, message = "Characters in Employee name must be in range: [3,15]")
    private String name;

    @NotBlank(message = "Employee email cannot be blank")
    @Email(message = "Employee email must be in valid format")
    private String email;

    @NotBlank(message = "Employee role cannot be left blank")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "Employee age cannot be null")
    @Min(value = 18, message = "Employee age must not be less than 18")
    @Max(value = 70, message = "Employee age must not be greater than 70")
    private Integer age;

    @NotNull(message = "Employee Salary cannot be null")
    @Positive(message = "Employee salary must be positive")
    @Min(value=150, message = "Employee salary must be minimum 150")
    @Max(value=1000000, message = "Employee salary must be maximum 1000000")
    private Integer salary;

    @PastOrPresent(message = "Employee's dateOfJoining cannot be in future")
    private LocalDate dateOfJoining;

    @NotNull(message = "Employee's isActive cannot be null")
    @JsonProperty("isActive")
    private Boolean isActive;

    @NotBlank(message = "Employee password cannot be blank")
    @PasswordValidation
    private String password;

    @NotNull(message = "Prime number cannot be null")
    @Positive(message = "Prime number must be positive")
    @PrimeNumberValidation
    private Integer primeNumber;
}
