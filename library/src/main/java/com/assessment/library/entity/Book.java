package com.assessment.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_generator")
    @SequenceGenerator(name = "book_generator", sequenceName = "book_id_seq", allocationSize = 1)

    private Long id;
    @Column
    private String isbn;
    @Column
    private String title;
    @Column
    private String author;

}
