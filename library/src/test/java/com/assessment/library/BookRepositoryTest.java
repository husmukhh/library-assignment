package com.assessment.library;

import com.assessment.library.entity.Book;
import com.assessment.library.repository.BookBorrowerRepository;
import com.assessment.library.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TestEntityManager entityManager;
    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfBookExistsByAuthorTitleISBN() {
        String isbn = "123456789012";
        //given
        Book book = new Book();
        book.setTitle("Test Book");
        book.setIsbn(isbn);
        book.setAuthor("Author-A");

        book = entityManager.persist(book);
        entityManager.flush();
        //when

        Book expected = bookRepository.findByAuthorAndTitleAndIsbn(book.getAuthor(),book.getTitle(),book.getIsbn());
        //then

        assertThat(expected.getId()).isEqualTo(book.getId());
        assertThat(expected.getIsbn()).isEqualTo(isbn);
    }


    @Test
    void itShouldCheckIfAllBooksLoaded() {
        String isbn = "123456789012";
        //given
        Book book = new Book();
        book.setTitle("Test Book");
        book.setIsbn(isbn);
        book.setAuthor("Author-A");

        entityManager.persist(book);
        entityManager.flush();
        String isbn2 = "123456789013";
        //given
        Book book2 = new Book();
        book2.setTitle("Test Book 2");
        book2.setIsbn(isbn);
        book2.setAuthor("Author-B");
        entityManager.persist(book2);
        entityManager.flush();
        //when

        List<Book> bookList = (List<Book>) bookRepository.findAll();
        //then

        assertThat(bookList.size()).isEqualTo(2);
    }

}
