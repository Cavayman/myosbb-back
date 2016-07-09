package com.softserve.osbb.service;

import com.softserve.osbb.model.Report;
import com.softserve.osbb.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by nazar.dovhyy on 09.07.2016.
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report addReport(Report report) {
        return null;
    }

    @Override
    public Report getReportById(Integer reportId) {
        return null;
    }

    @Override
    public Report getReportByName(String name) {
        return null;
    }

    @Override
    public List<Report> getAllReportsBySearchTerm(String searchTerm) {
        return null;
    }

    @Override
    public List<Report> getAllReportsBetweenDates(LocalDateTime from, LocalDateTime to) {
        return null;
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public List<Report> showLatestRepors() {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteReportById(Integer reportId) {

    }
}
