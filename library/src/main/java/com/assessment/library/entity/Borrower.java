package com.assessment.library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Borrower {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE,generator = "borrower_generator" )
    @SequenceGenerator(name = "borrower_generator", sequenceName = "borrower_id_seq", allocationSize = 1)

    private Long id;
    @Column
    private String borrowerName;
    @Column
    private String emailId;
}
