package com.assessment.library.controller;

import com.assessment.library.dto.BookDTO;
import com.assessment.library.dto.BorrowerDTO;
import com.assessment.library.request.BorrowerRequest;
import com.assessment.library.response.DTOResponseMessage;
import com.assessment.library.service.BookService;
import com.assessment.library.service.BorrowerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {
    private static final Logger logger = LoggerFactory.getLogger(BorrowerController.class);
    private BorrowerService borrowerService;
    public BorrowerController(BorrowerService borrowerService){
        this.borrowerService = borrowerService;
    }
    @Tag(name = "post", description = "Add Borrower API")
    @PostMapping(value = "/addBorrower" , consumes = "application/json" ,produces = "application/json")
    public ResponseEntity<DTOResponseMessage> addBorrower(@Valid  @RequestBody BorrowerRequest borrowerRequest){
        logger.info("Entered in addBorrower()  ");
        DTOResponseMessage message = new DTOResponseMessage();
        ResponseEntity<DTOResponseMessage> entity;
        HttpHeaders headers = new HttpHeaders();
        BorrowerDTO borrowerDTO = borrowerService.addBorrower(borrowerRequest);

        if(borrowerDTO == null){
            message.setMessage("EmailId already exists !");
            entity = new ResponseEntity<>(message,headers, HttpStatus.NOT_ACCEPTABLE);
        }else{
            message.setMessage("Borrower added successfully.");
            message.setId(borrowerDTO.getId());
            entity = new ResponseEntity<>(message,headers, HttpStatus.CREATED);
        }
        logger.info("End of addBorrower()  ");
        return entity;
    }
}
