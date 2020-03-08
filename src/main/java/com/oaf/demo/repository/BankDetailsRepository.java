package com.oaf.demo.repository;

import com.oaf.demo.model.BankDetails;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BankDetailsRepository implements JpaRepository<Long, BankDetails> {

    @Override
    public List<Long> findAll() {
        return null;
    }

    @Override
    public List<Long> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Long> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Long> findAllById(Iterable<BankDetails> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(BankDetails bankDetails) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void deleteAll(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Long> S save(S s) {
        return null;
    }

    @Override
    public <S extends Long> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Long> findById(BankDetails bankDetails) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(BankDetails bankDetails) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Long> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Long getOne(BankDetails bankDetails) {
        return null;
    }

    @Override
    public <S extends Long> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Long> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Long> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Long> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Long> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Long> boolean exists(Example<S> example) {
        return false;
    }
}
