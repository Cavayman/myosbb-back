package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.Attachment;
import com.softserve.osbb.model.Contract;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


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
    public void init(){
        contract = new Contract();
        //contract.setContractId(6);
        //contract.setPrice(BigDecimal.valueOf(2016));
        contract.setText("Text");
//        contract.setAttachment(new Attachment());
    }

    @Test
    public void testGetAllContracts(){
        List<Contract> list = Arrays.asList(new Contract(), new Contract());
        contractRepository.deleteAll();
        contractRepository.save(list);
        Assert.assertTrue(list.size() == contractRepository.findAll().size());
    }

    @Test
    public void testSave(){
        contract = contractRepository.save(contract);
        Assert.assertNotNull(contract.getContractId());
    }

    @Test
    public void testDeleteContract(){
        testSave();
        contractRepository.delete(contract);
        Assert.assertFalse(contractRepository.exists(contract.getContractId()));
    }

}
