package com.assessment.library.controller;

import com.assessment.library.dto.BookBorrowerDTO;
import com.assessment.library.request.BookBorrowReturnRequest;
import com.assessment.library.response.DTOResponseMessage;
import com.assessment.library.service.BookBorrowerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
public class BookBorrowerController {

    private static final Logger logger = LoggerFactory.getLogger(BookBorrowerController.class);
    private BookBorrowerService bookBorrowerService;

    public BookBorrowerController (BookBorrowerService bookBorrowerService){
        this.bookBorrowerService = bookBorrowerService;
    }
    @Tag(name = "post", description = " Issue book  API")
    @PostMapping("/issueBook")
    public ResponseEntity<DTOResponseMessage> borrowBook(@Valid @RequestBody BookBorrowReturnRequest bookBorrowerRequest){
        logger.info("Entered borrowBook()");
        DTOResponseMessage message = new DTOResponseMessage();

        BookBorrowerDTO dto = bookBorrowerService.borrowBook(bookBorrowerRequest);
        message.setId(dto.getId());
        message.setMessage("Book has been issued successfully.");
        ResponseEntity<DTOResponseMessage> entity = new ResponseEntity<>(message, HttpStatus.CREATED);
        logger.info("End borrowBook()");
        return entity;
    }

    @Tag(name = "post", description = "Return Book API")
    @PostMapping("/returnBook")
    public ResponseEntity<DTOResponseMessage> returnBook(@Valid @RequestBody BookBorrowReturnRequest bookBorrowerRequest){
        logger.info("Entered returnBook()");
        DTOResponseMessage message = new DTOResponseMessage();
        BookBorrowerDTO dto = bookBorrowerService.returnBook(bookBorrowerRequest);
        message.setId(dto.getId());
        message.setMessage("Book has been returned successfully.");
        ResponseEntity<DTOResponseMessage> entity = new ResponseEntity<>(message, HttpStatus.OK);
        logger.info("Ended returnBook()");
        return entity;
    }

}
