package com.softserve.osbb.service;

import com.softserve.osbb.model.Report;
import com.softserve.osbb.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazar.dovhyy on 09.07.2016.
 */
@Service
public class ReportServiceImpl implements ReportService {

    private static final List<Report> EMPTY_LIST = new ArrayList<Report>();
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
    public Report updateReport(Report report) throws Exception {

        return report == null? null: updateReportIfExists(report);
    }

    private Report updateReportIfExists(Report report) throws Exception {

        boolean isExisted = reportRepository.equals(report);

        if(!isExisted){
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
    public Report getOneReportBySearchTerm(String name) {
        return reportRepository.getAllReportsBySearchParam(name == null ? "" : name)
                .stream()
                .findFirst()
                .get();
    }

    @Override
    public List<Report> getAllReportsBySearchTerm(String searchTerm) {

        if(searchTerm == null || searchTerm.isEmpty()){
            return EMPTY_LIST;
        }

        return reportRepository.getAllReportsBySearchParam(searchTerm);
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
