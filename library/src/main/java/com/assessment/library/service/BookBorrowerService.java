package com.assessment.library.service;

import com.assessment.library.controller.BookBorrowerController;
import com.assessment.library.dto.BookBorrowerDTO;
import com.assessment.library.entity.BookBorrower;
import com.assessment.library.exception.BookAlreadyIssuedException;
import com.assessment.library.exception.RecordNotFoundException;
import com.assessment.library.repository.BookBorrowerRepository;
import com.assessment.library.request.BookBorrowReturnRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookBorrowerService {
    private static final Logger logger = LoggerFactory.getLogger(BookBorrowerService.class);
    private BookBorrowerRepository bookBorrowerRepository;
    public BookBorrowerService(BookBorrowerRepository bookBorrowerRepository){
        this.bookBorrowerRepository = bookBorrowerRepository;
    }

    public BookBorrowerDTO borrowBook(BookBorrowReturnRequest bookBorrowerRequest){
        logger.info("Entered in BookBorrowerService.borrowBook()");
        BookBorrower bookBorrowerExists = bookBorrowerRepository.findFirstByBookIdOrderByIdDesc(bookBorrowerRequest.getBookId());
        if(bookBorrowerExists != null && bookBorrowerExists.getBookId() != null && bookBorrowerExists.getReturnDate() == null){
            logger.info("Error : record already exists book id{} , borrower id {} ", bookBorrowerExists.getBookId(),
                    bookBorrowerExists.getBorrowerId());
            throw new BookAlreadyIssuedException("This book is already issued.");
        }else{
            BookBorrower bookBorrower = new BookBorrower();
            bookBorrower.setBookId(bookBorrowerRequest.getBookId());
            bookBorrower.setBorrowerId(bookBorrowerRequest.getBorrowerId());
            bookBorrower.setIssueDate(new Date(System.currentTimeMillis()));
            bookBorrower.setRemarks(bookBorrowerRequest.getRemarks());
            bookBorrower = bookBorrowerRepository.save(bookBorrower);
            logger.info("BookBorrower record saved successfully.");
            BookBorrowerDTO dto = new BookBorrowerDTO();
            dto.setId(bookBorrower.getId());
            return dto;
        }

    }

    public BookBorrowerDTO returnBook(BookBorrowReturnRequest bookBorrowReturnRequest){
        logger.info("Entered in BookBorrowerService.returnBook()");
        BookBorrower bookBorrower = bookBorrowerRepository.findFirstByBookIdAndBorrowerIdOrderByIdDesc(bookBorrowReturnRequest.getBookId(),
                bookBorrowReturnRequest.getBorrowerId());
        if((bookBorrower == null || bookBorrower.getBorrowerId() == null)  || bookBorrower.getReturnDate() != null){
            logger.info("BookBorrowerService.returnBook() -- invalid request returned date{}", bookBorrower.getReturnDate());
            throw new RecordNotFoundException("Given record does not exists.");
        }else{
            bookBorrower.setReturnDate(new Date(System.currentTimeMillis()));
            bookBorrower = bookBorrowerRepository.save(bookBorrower);
            logger.info("Book returned successfully. book id {}  returned date : {}" , bookBorrower.getBookId(),
                    bookBorrower.getReturnDate());
            BookBorrowerDTO dto = new BookBorrowerDTO();
            dto.setId(bookBorrower.getId());
            return dto;
        }
    }

}
