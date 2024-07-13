package com.assessment.library.controller;

import com.assessment.library.response.DTOResponseMessage;
import com.assessment.library.response.ListResponseMessage;
import com.assessment.library.response.ResponseMessage;
import com.assessment.library.dto.BookDTO;
import com.assessment.library.request.BookRequest;
import com.assessment.library.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private BookService bookService;
    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    @Tag(name = "post", description = "Add Book API")
    @PostMapping(value = "/addBook",consumes = "application/json" ,produces = "application/json")
    public ResponseEntity<DTOResponseMessage> addBook(@Valid @RequestBody BookRequest request){
        logger.info("Entered in addBook()");
        BookDTO bookDTO = bookService.addBook(request);
        HttpHeaders headers = new HttpHeaders();
        DTOResponseMessage message = new DTOResponseMessage();
        message.setId(bookDTO.getId());
        message.setMessage("Book stored successfully");
        ResponseEntity<DTOResponseMessage> entity= new ResponseEntity<>(message,headers, HttpStatus.CREATED);
        logger.info("End of addBook()");
        return entity;
    }

        @Tag(name = "get", description = "Book List API")
        @GetMapping(value = "getAllBooks" , produces = "application/json")
        public ResponseEntity<ListResponseMessage> getAllBooks(){
            logger.info("Entered in getAllBooks()");
            List<BookDTO>  bookDTOList = bookService.getAllBooks();
            ListResponseMessage<BookDTO> listResponseMessage = new ListResponseMessage<>();
            listResponseMessage.setMessage("All books");
            listResponseMessage.setData(bookDTOList);
            HttpHeaders headers = new HttpHeaders();
            ResponseEntity<ListResponseMessage> entity = new ResponseEntity<>(listResponseMessage,headers,HttpStatus.OK);
            logger.info("End of getAllBooks()");
            return  entity;
        }
}
