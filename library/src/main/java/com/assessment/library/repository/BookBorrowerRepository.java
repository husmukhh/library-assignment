package com.assessment.library.repository;

import com.assessment.library.entity.BookBorrower;
import org.springframework.data.repository.CrudRepository;

public interface BookBorrowerRepository extends CrudRepository<BookBorrower,Long> {

    BookBorrower findFirstByBookIdOrderByIdDesc(Long bookId);
    BookBorrower findFirstByBookIdAndBorrowerIdOrderByIdDesc(Long bookId, Long borrowerId);
}
