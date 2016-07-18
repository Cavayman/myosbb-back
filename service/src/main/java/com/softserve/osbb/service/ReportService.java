package com.softserve.osbb.service;

import com.softserve.osbb.model.Report;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by nazar.dovhyy on 09.07.2016.
 */

@Service
public interface ReportService {

    Report addReport(Report report) throws Exception;

    Report updateReport(Integer reportId, Report report) throws Exception;

    Report getReportById(Integer reportId) throws Exception;

    Report getOneReportBySearchTerm(String name) throws Exception;

    List<Report> getAlReportsBySearchParameter(String searchTerm) throws Exception;

    List<Report> getAllReportsBetweenDates(LocalDate from, LocalDate to) throws Exception;

    List<Report> getAllReports() throws Exception;

    List<Report> findByDate(LocalDate dateToFind);

    void deleteAll() throws Exception;

    void deleteReportById(Integer reportId) throws Exception;


}
