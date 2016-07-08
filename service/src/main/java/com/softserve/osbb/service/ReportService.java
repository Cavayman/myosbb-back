package com.softserve.osbb.service;

import com.softserve.osbb.model.Report;

import java.util.List;

/**
 * Created by nazar.dovhyy on 09.07.2016.
 */
public interface ReportService {

    Report addReport(Report report);

    Report getReportById(int reportId);

    Report getReportByName(String name);

    List<Report> getAllReports();

}
