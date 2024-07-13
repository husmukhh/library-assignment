package com.assessment.library.controller;

import com.assessment.library.dto.BorrowerDTO;
import com.assessment.library.response.DTOResponseMessage;
import com.assessment.library.response.ListResponseMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class BorrowerControllerTest extends BaseControllerTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testAddBorrower() throws Exception {
        // Setup
        // Run the test
        String uri = "/borrower/addBorrower";
        BorrowerDTO borrowerDTO = new BorrowerDTO();
        borrowerDTO.setName("Test Borrower");
        borrowerDTO.setEmailId("test@gmail.com");
        String borrowerJson = super.mapToJson(borrowerDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON_VALUE).content(borrowerJson)
        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        // Verify the results
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);

        String content = mvcResult.getResponse().getContentAsString();
        DTOResponseMessage responseMessage = super.mapFromJson(content,  DTOResponseMessage.class);

        assertEquals("Borrower added successfully.", responseMessage.getMessage());
        assertTrue(responseMessage.getId() > 0);
    }
}
