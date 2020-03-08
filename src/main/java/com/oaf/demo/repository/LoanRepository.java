package com.oaf.demo.repository;

import com.oaf.demo.model.Loan;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("SELECT loan.loanAmount FROM Loan loan where loan.id = ?1")
    Long getLoanAmount(Long id);


}
