package com.assessment.library.respository;

import com.assessment.library.entity.BookBorrower;
import com.assessment.library.repository.BookBorrowerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Date;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class BookBorrowerRepositoryTest {

    @Autowired
    BookBorrowerRepository bookBorrowerRepository;
    @Autowired
    private TestEntityManager entityManager;

    @AfterEach
    void tearDown() {
        bookBorrowerRepository.deleteAll();
    }

    @Test
    public void checkBookIssued(){
        BookBorrower bookBorrower = new BookBorrower();
        bookBorrower.setBorrowerId(1L);
        bookBorrower.setBookId(2L);
        bookBorrower.setIssueDate(new Date(System.currentTimeMillis()));
        bookBorrower.setRemarks("To test ");
        bookBorrower = entityManager.persist(bookBorrower);

        BookBorrower bookBorrower1 = bookBorrowerRepository.findFirstByBookIdOrderByIdDesc(2L);

        assertThat(bookBorrower1.getId()).isEqualTo(bookBorrower.getId());
        assertThat(bookBorrower1.getBookId()).isEqualTo(bookBorrower.getBookId());
        assertThat(bookBorrower1.getBorrowerId()).isEqualTo(bookBorrower.getBorrowerId());

    }

    @Test
    public void checkByBookIdAndBorrowerIdTest(){
        BookBorrower bookBorrower = new BookBorrower();
        bookBorrower.setBorrowerId(1L);
        bookBorrower.setBookId(2L);
        bookBorrower.setIssueDate(new Date(System.currentTimeMillis()));
        bookBorrower.setRemarks("To test ");
        bookBorrower = entityManager.persist(bookBorrower);

        BookBorrower bookBorrower1 = bookBorrowerRepository.findFirstByBookIdAndBorrowerIdOrderByIdDesc(2L,1L);

        assertThat(bookBorrower1.getId()).isEqualTo(bookBorrower.getId());
        assertThat(bookBorrower1.getBookId()).isEqualTo(bookBorrower.getBookId());
        assertThat(bookBorrower1.getBorrowerId()).isEqualTo(bookBorrower.getBorrowerId());

    }
}
