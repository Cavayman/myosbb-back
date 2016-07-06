package com.softserve.osbb.repository;

import com.softserve.osbb.OsbbApplicationRunner;
import com.softserve.osbb.repository.ContractRepository;

import com.softserve.osbb.model.ContractEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Roma on 06/07/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration (classes = OsbbApplicationRunner.class)
@Transactional
public class ContractRepositoryTest {

    @Autowired
    private ContractRepository contractRepository;

    private ContractEntity contractEntity;

    @Test
    public void init(){
        contractEntity = new ContractEntity();

        Date dateStart = null;
        try {
            dateStart = new SimpleDateFormat("yyyy-mm-dd")
                    .parse("2016-01-22");
        } catch (ParseException e) {
            dateStart = new Date();
        }

        contractEntity = new ContractEntity();
        contractEntity.setDatestart(dateStart);



    }


}
