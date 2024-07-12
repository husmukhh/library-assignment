package com.assessment.library;

import com.assessment.library.entity.Borrower;
import com.assessment.library.repository.BorrowerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class BorrowerRepositoryTest {
    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @AfterEach
    void tearDown() {
        borrowerRepository.deleteAll();
    }
    @Test
    public void checkEmailIdExists(){
        Borrower borrower = new Borrower();
        borrower.setEmailId("husmukh@gmail.com");
        borrower.setBorrowerName("Husmukh Maheshwari");

        borrower = entityManager.persist(borrower);

        Borrower borrower1 = borrowerRepository.findByEmailId("husmukh@gmail.com");
        assertThat(borrower.getId()).isEqualTo(borrower1.getId());
        assertThat(borrower.getBorrowerName()).isEqualTo(borrower1.getBorrowerName());
    }
}
