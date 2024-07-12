package com.assessment.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class BookBorrower {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,generator = "book_borrower_generator" )
    @SequenceGenerator(name = "book_borrower_generator", sequenceName = "book_borrower_id_seq", allocationSize = 1)
    private Long id;
    @Column
    private Long bookId;
    @Column
    private Long borrowerId;
    @Column
    private Date issueDate;
    @Column
    private Date returnDate;
    private String remarks;

}
