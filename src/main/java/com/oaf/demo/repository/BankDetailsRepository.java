package com.oaf.demo.repository;

import com.oaf.demo.model.BankDetails;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {


    @Query("UPDATE BankDetails bank_details SET bank_details.amount = ?1 WHERE bank_details.id = ?2")
    @Transactional
    @Modifying
    void updateAccount(Double amount, Long id);


    @Query("SELECT bank_details.amount FROM BankDetails bank_details where bank_details.id = ?1")
    Double getAccountBalance (Long id);

}
