package com.assessment.library.controller;

import com.assessment.library.dto.BookDTO;
import com.assessment.library.response.DTOResponseMessage;
import com.assessment.library.response.ListResponseMessage;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookControllerTest extends  BaseControllerTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void getBooksTest() throws Exception {
        String uri = "/book/getAllBooks";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        ListResponseMessage responseMessage = super.mapFromJson(content, ListResponseMessage.class);
        assertTrue(responseMessage.getMessage().equals("All books"));
    }

    @Test
    public void addBookTest() throws Exception {

        String uri = "/book/addBook";
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn("123456789012");
        bookDTO.setAuthor("Husmukh M");
        bookDTO.setTitle("Test Add Book");
        String bookJson = super.mapToJson(bookDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(bookJson)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        DTOResponseMessage responseMessage = super.mapFromJson(content, DTOResponseMessage.class);
        assertTrue(responseMessage.getMessage().equals("Book stored successfully"));
        assertTrue(responseMessage.getId() > 0);
    }
}
