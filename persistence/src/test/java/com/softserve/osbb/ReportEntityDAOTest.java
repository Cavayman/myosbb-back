package com.softserve.osbb;

import com.softserve.osbb.dao.ReportDAO;
import com.softserve.osbb.model.ReportEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;


/**
 * Created by nazar.dovhyy on 04.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OsbbApplicationRunner.class)
public class ReportEntityDAOTest {

    private static Logger logger = LoggerFactory.getLogger(ReportEntityDAOTest.class);

    @Autowired
    private ReportDAO reportDAO;

    private ReportEntity reportEntity;

    @Before
    public void init(){
        reportEntity = new ReportEntity();
        reportEntity.setName("баланс ЧЕРВ/2016");
        reportEntity.setDatecreation(LocalDate.of(2016, 02, 12));

    }


    @Test
    public void testSaveReport(){

        reportEntity = reportDAO.save(reportEntity);

        logger.info(""+reportEntity);

        assertNotNull(reportEntity);

    }

}
