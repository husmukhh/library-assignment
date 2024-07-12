package com.assessment.library.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class BookBorrowReturnRequest {
    @NotNull(message = "book id is required.")
    @Positive
    private Long bookId;
    @NotNull(message = "borrower id is required.")
    @Positive
    private Long borrowerId;
    private String remarks;

}
