package com.assessment.library.dto;

import com.assessment.library.request.BookRequest;

public class BookDTO extends BookRequest {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
