package com.assessment.library.service;

import com.assessment.library.controller.BorrowerController;
import com.assessment.library.dto.BorrowerDTO;
import com.assessment.library.entity.Borrower;
import com.assessment.library.exception.BookAlreadyExistsException;
import com.assessment.library.exception.BorrowerAlreadyExistException;
import com.assessment.library.repository.BorrowerRepository;
import com.assessment.library.request.BorrowerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BorrowerService {
    private static final Logger logger = LoggerFactory.getLogger(BorrowerController.class);
    private BorrowerRepository borrowerRepository;
    public BorrowerService(BorrowerRepository borrowerRepository){
        this.borrowerRepository = borrowerRepository;
    }
    public BorrowerDTO addBorrower(BorrowerRequest borrowerRequest){

        Borrower borrowerExists =  borrowerRepository.findByEmailId(borrowerRequest.getEmailId());
        if(borrowerExists != null && borrowerExists.getEmailId() != null ){
            throw new BorrowerAlreadyExistException("Borrower already registered with  email : " + borrowerRequest.getEmailId());
        }else{
            Borrower borrower = new Borrower();
            borrower.setBorrowerName(borrowerRequest.getName());
            borrower.setEmailId(borrowerRequest.getEmailId());
            borrower = borrowerRepository.save(borrower);
            BorrowerDTO borrowerDTO = new BorrowerDTO();
            borrowerDTO.setId(borrower.getId());
            borrowerDTO.setEmailId(borrower.getEmailId());
            borrowerDTO.setName(borrower.getBorrowerName());
            return borrowerDTO;
        }

    }
}
