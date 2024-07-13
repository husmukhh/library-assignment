package com.assessment.library.service;

import com.assessment.library.dto.BookDTO;
import com.assessment.library.entity.Book;
import com.assessment.library.exception.BookAlreadyExistsException;
import com.assessment.library.repository.BookRepository;
import com.assessment.library.request.BookRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookBorrowerService.class);
    private BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public BookDTO addBook(BookRequest bookRequest){

        Book bookExists = bookRepository.findByAuthorAndTitleAndIsbn(bookRequest.getAuthor(),bookRequest.getTitle(),bookRequest.getIsbn());
        if(bookExists == null || bookExists.getId() == 0) {
            Book book = new Book();
            book.setAuthor(bookRequest.getAuthor());
            book.setIsbn(bookRequest.getIsbn());
            book.setTitle(bookRequest.getTitle());
            try {
                book = bookRepository.save(book);
                logger.info("Book saved successfully : book id : {}" , book.getId());
                BookDTO bookDTO = new BookDTO();
                bookDTO.setId(book.getId());
                return bookDTO;
            } catch (Exception sqlExe) {
                logger.error("Error occured in addBook : {}" , sqlExe.getMessage());
                throw sqlExe;
            }
        }else{
            logger.info("Book already exists : book id : {}", bookExists.getId());
            throw new BookAlreadyExistsException("Book already exists !");
        }
    }

    public List<BookDTO> getAllBooks() {
        logger.info("Entered getAllBooks");
        List<Book> bookList = (List<Book>) bookRepository.findAll();
        List<BookDTO> bookDTOList = new ArrayList<>();
        for(Book book : bookList){
            BookDTO bookDTO = new BookDTO();
            bookDTO.setTitle(book.getTitle());
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setId(book.getId());
            bookDTO.setIsbn(book.getIsbn());
            bookDTOList.add(bookDTO);
        }
        logger.info("End of getAllBooks");
        return bookDTOList;
    }
}
