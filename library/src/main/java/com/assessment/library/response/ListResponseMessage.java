package com.assessment.library.response;

import lombok.Data;

import java.util.List;

@Data
public class ListResponseMessage<T> extends ResponseMessage{

    private List<T> data;
}
