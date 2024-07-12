package com.assessment.library.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    @NotEmpty(message = "ISBN is required.")
    private String isbn;

    @NotEmpty(message = "Title is required.")
    private String title;
    @NotEmpty(message = "Author is required.")
    private String author;
}
