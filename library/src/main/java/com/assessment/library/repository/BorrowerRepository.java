package com.assessment.library.repository;

import com.assessment.library.entity.Borrower;
import org.springframework.data.repository.CrudRepository;

public interface BorrowerRepository extends CrudRepository<Borrower,Long> {
    Borrower findByEmailId(String emailId);
}
