package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.Report;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by nazar.dovhyy on 04.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Transactional
public class ReportRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;
    private Report report;
    private List<Report> reportList;

    @Before
    public void init() {
        reportList = new ArrayList<>();
        report = new Report();
        report.setName("баланс ЧЕРВ/2016");
        LocalDate dateCreation = LocalDate.now();
        report.setCreationDate(dateCreation);
    }

    @After
    public void destroy() {
        reportList.clear();
        reportList = null;
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
        reportList.add(new Report("баланс ЛИП/2016", "фін. звіт за липень"));
        reportList.add(new Report("баланс СЕР/2016", "фін. звіт за серпень"));
        reportList.add(new Report("баланс ВЕР/2016", "фін. звіт за вересень"));
        List<Report> savedReports = reportRepository.save(reportList);
        assertSame(savedReports.size(), reportList.size());
    }

    @Test
    public void testDeleteAllReports() {
        reportRepository.deleteAll();
        assertTrue(reportRepository.findAll().isEmpty());

    }

    @Test
    public void testGetAllReportsByYear() {
        Report reportOne = new Report();
        reportOne.setName("report1");
        reportOne.setCreationDate(LocalDate.now().minusYears(2));
        reportList.add(reportOne);
        Report reportTwo = new Report();
        reportTwo.setName("report2");
        reportTwo.setCreationDate(LocalDate.now().minusYears(1));
        reportList.add(reportTwo);
        Report reportThree = new Report();
        reportThree.setName("report3");
        reportThree.setCreationDate(LocalDate.now().minusYears(2));
        reportList.add(reportThree);

        reportRepository.save(reportList);

        List<Report> reportsByYear = reportRepository.findByCreationDate(LocalDate.now().minusYears(2));
        assertTrue(reportsByYear.size() == 2);
    }

    @Test
    public void testFindDistinctNameReports() {
        reportList.add(new Report("marchReport2016", "some march report"));
        reportList.add(new Report("juneReport2015", "some march report"));
        reportList.add(new Report("marchReport2016", "march report updated"));
        reportList.add(new Report("juneReport2016", "june report updated"));
        reportRepository.save(reportList);
        List<Report> savedDistinctReports = reportRepository.findDistinctByName("marchReport2016");
        assertTrue(savedDistinctReports.size() == 2);

    }

    @Test
    public void testCountByName() {
        reportList.add(new Report("marchReport2016", "some march report"));
        reportList.add(new Report("juneReport2015", "some march report"));
        reportList.add(new Report("marchReport2016", "march report updated"));
        reportList.add(new Report("juneReport2016", "june report updated"));
        reportRepository.save(reportList);
        long countByName = reportRepository.countByName("marchReport2016");
        assertTrue(countByName == 2);
    }


}
