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
    public Contract save(Contract contract);

    public Contract findOne(Integer integer);

    public Contract findOne (String id);

    public boolean exists (Integer integer);

    public List<Contract> findAll();

    public List<Contract> findAll(Sort sort) ;

    public Page<Contract> findAll(Pageable pageable) ;

    public List<Contract> findAll(Iterable<Integer> iterable) ;

    public long count() ;

    public void delete(Integer integer) ;

    public void delete(Contract contract) ;

    public void delete(Iterable<? extends Contract> iterable) ;

    public void deleteAll();

    public void flush() ;

    public void deleteInBatch(Iterable<Contract> iterable) ;

    public void deleteAllInBatch() ;

    public Contract getOne(Integer integer);

    public Contract saveAndFlush(Contract contract) ;

    public List<Contract> save(Iterable<Contract> iterable) ;
}
