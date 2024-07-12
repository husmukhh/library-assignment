package com.assessment.library.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BorrowerRequest {
    @NotEmpty(message = "name is required.")
    private String name;
    @NotEmpty(message = "email is required.")
    @Email(message = " Invalid email address")
    private String emailId;

}
