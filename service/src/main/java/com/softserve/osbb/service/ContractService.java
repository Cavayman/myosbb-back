package com.softserve.osbb.service;

import com.softserve.osbb.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roma on 13/07/2016.
 * Assigned to Anastasiia on 20.07.2016
 */
@Service
public interface ContractService {
    Contract save(Contract contract);

    Contract findOne(Integer integer);

    Contract findOne (String id);

    boolean exists (Integer integer);

    List<Contract> findAll();

    List<Contract> findAll(Sort sort) ;

    Page<Contract> findAll(Pageable pageable) ;

    List<Contract> findAll(Iterable<Integer> iterable) ;

    long count() ;

    void delete(Integer integer) ;

    void delete(Contract contract) ;

    void delete(Iterable<? extends Contract> iterable) ;

    void deleteAll();

    void flush() ;

    void deleteInBatch(Iterable<Contract> iterable) ;

    void deleteAllInBatch() ;

    Contract getOne(Integer integer);

    Contract saveAndFlush(Contract contract) ;

    List<Contract> save(Iterable<Contract> iterable) ;

    Page<Contract> getContracts(Integer pageNumber, String sortBy, Boolean order);

    List<Contract> findContractsByProviderName(String name);

    Page<Contract> findByActiveTrue(Integer pageNumber, String sortBy, Boolean order);
    List<Contract> findByActiveTrue();
}
