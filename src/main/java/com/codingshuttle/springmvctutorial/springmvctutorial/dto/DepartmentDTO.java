package com.codingshuttle.springmvctutorial.springmvctutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;

    @NotBlank(message = "Department title cannot be blank")
    @Size(min=2, max=20, message = "Characters in Department title must be in range: [2,20]")
    private String title;

    @PastOrPresent(message = "Department creation date cannot be in future")
    private LocalDate createdAt;

    @NotNull(message = "Department isActive cannot be null")
    @JsonProperty("isActive")
    private Boolean isActive;
}
