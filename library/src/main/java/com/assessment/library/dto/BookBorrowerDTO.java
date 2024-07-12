package com.assessment.library.dto;

import lombok.Data;

@Data
public class BookBorrowerDTO {
    private Long id;
    private Long bookId;
    private Long borrowerId;

    private String remarks;

}
