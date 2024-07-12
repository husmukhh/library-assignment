package com.assessment.library.service;

import com.assessment.library.dto.BookBorrowerDTO;
import com.assessment.library.entity.BookBorrower;
import com.assessment.library.exception.BookAlreadyIssuedException;
import com.assessment.library.exception.RecordNotFoundException;
import com.assessment.library.repository.BookBorrowerRepository;
import com.assessment.library.request.BookBorrowReturnRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookBorrowerService {

    private BookBorrowerRepository bookBorrowerRepository;
    public BookBorrowerService(BookBorrowerRepository bookBorrowerRepository){
        this.bookBorrowerRepository = bookBorrowerRepository;
    }

    public BookBorrowerDTO borrowBook(BookBorrowReturnRequest bookBorrowerRequest){

        BookBorrower bookBorrowerExists = bookBorrowerRepository.findFirstByBookIdOrderByIdDesc(bookBorrowerRequest.getBookId());
        if(bookBorrowerExists != null && bookBorrowerExists.getBookId() != null && bookBorrowerExists.getReturnDate() == null){
            throw new BookAlreadyIssuedException("This book is already issued.");
        }else{
            BookBorrower bookBorrower = new BookBorrower();
            bookBorrower.setBookId(bookBorrowerRequest.getBookId());
            bookBorrower.setBorrowerId(bookBorrowerRequest.getBorrowerId());
            bookBorrower.setIssueDate(new Date(System.currentTimeMillis()));
            bookBorrower.setRemarks(bookBorrowerRequest.getRemarks());
            bookBorrower = bookBorrowerRepository.save(bookBorrower);
            BookBorrowerDTO dto = new BookBorrowerDTO();
            dto.setId(bookBorrower.getId());
            return dto;
        }
    }

    public BookBorrowerDTO returnBook(BookBorrowReturnRequest bookBorrowReturnRequest){
        BookBorrower bookBorrower = bookBorrowerRepository.findFirstByBookIdAndBorrowerIdOrderByIdDesc(bookBorrowReturnRequest.getBookId(),
                bookBorrowReturnRequest.getBorrowerId());
        if((bookBorrower == null || bookBorrower.getBorrowerId() == null)  || bookBorrower.getReturnDate() != null){
            throw new RecordNotFoundException("Given record does not exists.");
        }else{
            bookBorrower.setReturnDate(new Date(System.currentTimeMillis()));
            bookBorrower = bookBorrowerRepository.save(bookBorrower);

            BookBorrowerDTO dto = new BookBorrowerDTO();
            dto.setId(bookBorrower.getId());
            return dto;
        }
    }

}
