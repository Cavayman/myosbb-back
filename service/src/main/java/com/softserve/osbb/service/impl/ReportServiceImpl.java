package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Report;
import com.softserve.osbb.repository.ReportRepository;
import com.softserve.osbb.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by nazar.dovhyy on 09.07.2016.
 */
@Service
public class ReportServiceImpl implements ReportService {

    private static final List<Report> EMPTY_LIST = new CopyOnWriteArrayList<>();
    private static final Report EMPTY_REPORT = new Report();

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report addReport(Report report) throws Exception {

        return report == null ? null : addReportIfNotExists(report);
    }

    private Report addReportIfNotExists(Report report) throws Exception {

        boolean isExisted = reportRepository.exists(report.getReportId());

        if (isExisted) {
            throw new Exception("report under id: " + report.getReportId() + " already exists");
        }

        return reportRepository.save(report);

    }

    @Override
    public Report updateReport(Integer reportId, Report report) throws Exception {

        return report == null ? null : updateReportIfExists(reportId, report);
    }

    private Report updateReportIfExists(Integer reportId, Report report) throws Exception {

        boolean isExisted = reportRepository.exists(reportId);

        if (!isExisted) {
            throw new Exception("report under id: " + report.getReportId() + " doesn't exist and thus" +
                    " cannot be updated");

        }
        return reportRepository.save(report);

    }


    @Override
    public Report getReportById(Integer reportId) {
        return reportRepository.findOne(reportId);
    }

    @Override
    public Report getOneReportBySearchTerm(String searchTerm) {
        return (searchTerm != null || !searchTerm.isEmpty()) ?
                reportRepository.getAllReportsBySearchParam(searchTerm)
                        .stream()
                        .findFirst()
                        .get() : EMPTY_REPORT;
    }

    @Override
    public List<Report> getAllReportsBySearchTerm(String searchTerm) {
        return (searchTerm == null || searchTerm.isEmpty()) ? EMPTY_LIST :
                reportRepository.getAllReportsBySearchParam(searchTerm);
    }

    @Override
    public List<Report> getAllReportsBetweenDates(LocalDateTime from, LocalDateTime to) {
        return reportRepository.gerAllReportsBetweenDates(from == null ? LocalDateTime.now() : from,
                to == null ? LocalDateTime.now() : to);
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
        reportRepository.deleteAll();

    }

    @Override
    public void deleteReportById(Integer reportId) {
        reportRepository.delete(reportId);
    }
}
