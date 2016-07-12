package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.Contract;

import com.softserve.osbb.model.Osbb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;


/**
 * Created by Roma on 06/07/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = PersistenceConfiguration.class)
@Transactional
public class ContractRepositoryTest {
    private Contract contract;

    @Autowired
    private ContractRepository contractRepository;

    @Before
    public void setUpToContractObject(){
        contract = new Contract();

        contract.setContractId(1);
        contract.setDateStart(new java.util.Date(System.currentTimeMillis()));
        contract.setDateFinish(new java.util.Date(System.currentTimeMillis()));
        contract.setDocument("Contract for Cleaning, Housekeeping, and/or Janitorial Services");
        contract.setPrice(BigDecimal.valueOf(2312));
        contract.setText("This contract is made between");

    }

    @Test
    public void testToSizeString(){
        assertEquals(0,contractRepository.findAll().size());
        contractRepository.save(contract);
        assertEquals(1,contractRepository.findAll().size());
    }

    @After
    public void afterTest(){
        contractRepository.delete(contract);
    }



}
