package com.softserve.osbb;

import com.softserve.osbb.dao.ReportRepository;
import com.softserve.osbb.model.ReportEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;



/**
 * Created by nazar.dovhyy on 04.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OsbbApplicationRunner.class)
@Transactional
public class ReportEntityRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

    private ReportEntity reportEntity;

    @Before
    public void init(){

        reportEntity = new ReportEntity();

        Date dateCreation = null;
        try {
            dateCreation = new SimpleDateFormat("yyyy-mm-dd")
                    .parse("2016-01-22");
        } catch (ParseException e) {
            dateCreation = new Date();
        }

        reportEntity = new ReportEntity();
        reportEntity.setName("баланс ЧЕРВ/2016");
        reportEntity.setDatecreation(dateCreation);
    }


    @Test
    public void testSaveReport(){

        reportEntity = reportRepository.save(reportEntity);

        assertNotNull(reportEntity);

        assertTrue(reportRepository.exists(reportEntity.getReportId()));

    }

    @Test
    public void testUpdateReport(){

        reportEntity = reportRepository.save(reportEntity);

        reportEntity.setName("баланс ЧЕРВ/2016 2.0");

        ReportEntity updatedReportEntity;
        updatedReportEntity = reportRepository.saveAndFlush(reportEntity);

        assertSame(reportEntity.getReportId(), updatedReportEntity.getReportId());
    }


    @Test
    public void testDeleteReport(){

        reportEntity = reportRepository.save(reportEntity);

        reportRepository.delete(reportEntity);

        assertFalse(reportRepository.exists(reportEntity.getReportId()));


    }

    @Test
    public void testGetAllReports(){

        List<ReportEntity> reportEntities = new ArrayList<>();

        reportEntities.add(new ReportEntity("баланс ЛИП/2016", "фін. звіт за липень"));
        reportEntities.add(new ReportEntity("баланс СЕР/2016", "фін. звіт за серпень"));
        reportEntities.add(new ReportEntity("баланс ВЕР/2016", "фін. звіт за вересень"));

        reportRepository.save(reportEntities);

        assertTrue(reportRepository.findAll().size() == reportEntities.size());

    }

    @Test
    public void testDeleteAllReports(){

        reportRepository.deleteAll();

        assertTrue(reportRepository.findAll().isEmpty());

    }

}
