package com.assessment.library.repository;

import com.assessment.library.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {
    Book findByAuthorAndTitleAndIsbn(String author, String title, String isbn);
}
