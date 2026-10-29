package com.example.recruitpro.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateCreateDTO {

    @NotBlank(message = "Full Name cannot be empty")
    @Size(min = 5, max = 50, message = "Full Name must be between 5 and 50 characters")
    private String fullName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is invalid")
    private String email;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

    @NotNull(message = "Years of experience cannot be null")
    @Min(value = 0, message = "Years of experience must be greater than or equal to 0")
    private Integer yearsOfExperience;
}
