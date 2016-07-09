package com.softserve.osbb.service;

import com.softserve.osbb.model.Report;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by nazar.dovhyy on 09.07.2016.
 */
public interface ReportService {

    Report addReport(Report report) throws Exception;

    Report updateReport(Report report) throws Exception;

    Report getReportById(Integer reportId) throws Exception;

    Report getOneReportBySearchTerm(String name) throws Exception;

    List<Report> getAllReportsBySearchTerm(String searchTerm) throws Exception;

    List<Report> getAllReportsBetweenDates(LocalDateTime from, LocalDateTime to) throws Exception;

    List<Report> getAllReports() throws Exception;

    List<Report> showLatestRepors() throws Exception;

    void deleteAll() throws Exception;

    void deleteReportById(Integer reportId) throws Exception;



}
