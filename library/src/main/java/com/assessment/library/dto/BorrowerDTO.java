package com.assessment.library.dto;

import com.assessment.library.request.BorrowerRequest;

public class BorrowerDTO extends BorrowerRequest {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
