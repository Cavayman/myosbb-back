package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.Report;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by nazar.dovhyy on 04.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
public class ReportRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

    private Report report;

    @Before
    public void init() {

        report = new Report();
        LocalDateTime dateCreation = LocalDateTime.now();
        report = new Report();
        report.setName("баланс ЧЕРВ/2016");
        report.setCreationDate(dateCreation);
    }


    @Test
    public void testSaveReport() {

        report = reportRepository.save(report);

        assertNotNull(report);

        assertTrue(reportRepository.exists(report.getReportId()));

    }

    @Test
    public void testUpdateReport() {

        report = reportRepository.save(report);

        report.setName("баланс ЧЕРВ/2016 2.0");

        Report updatedReport;
        updatedReport = reportRepository.saveAndFlush(report);

        assertSame(report.getReportId(), updatedReport.getReportId());
    }


    @Test
    public void testDeleteReport() {

        report = reportRepository.save(report);

        reportRepository.delete(report);

        assertFalse(reportRepository.exists(report.getReportId()));


    }

    @Test
    public void testGetAllReports() {

               List<Report> reportEntities = new ArrayList<>();

        reportEntities.add(new Report("баланс ЛИП/2016", "фін. звіт за липень"));
        reportEntities.add(new Report("баланс СЕР/2016", "фін. звіт за серпень"));
        reportEntities.add(new Report("баланс ВЕР/2016", "фін. звіт за вересень"));

        reportRepository.save(reportEntities);

        assertTrue(reportRepository.findAll().size() != reportEntities.size());
        System.out.println(reportRepository.findAll().size());
    }

    @Test
    public void testDeleteAllReports() {

        reportRepository.deleteAll();

        assertTrue(reportRepository.findAll().isEmpty());

    }



}
