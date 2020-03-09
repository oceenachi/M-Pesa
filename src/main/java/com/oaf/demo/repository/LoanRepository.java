package com.oaf.demo.repository;

import com.oaf.demo.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("SELECT loan.loanAmount FROM Loan loan where loan.id = ?1")
    Long getLoanAmount(Long id);


    @Transactional
    @Modifying
    @Query("UPDATE Loan loan SET loan.loanAmount = ?1 WHERE loan.id = ?2")
    void updateLoanAmount(Long amount, Long Id);


}
