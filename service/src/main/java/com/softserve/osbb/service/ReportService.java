package com.softserve.osbb.service;

import com.softserve.osbb.model.Report;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by nazar.dovhyy on 09.07.2016.
 */
public interface ReportService {

    Report addReport(Report report);

    Report getReportById(Integer reportId);

    Report getReportByName(String name);

    List<Report> getAllReportsBySearchTerm(String searchTerm);

    List<Report> getAllReportsBetweenDates(LocalDateTime from, LocalDateTime to);

    List<Report> getAllReports();

    List<Report> showLatestRepors();

    void deleteAll();

    void deleteReportById(Integer reportId);



}
