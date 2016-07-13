package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Report;
import com.softserve.osbb.repository.ReportRepository;
import com.softserve.osbb.service.ReportService;
import com.softserve.osbb.service.exe.NotFoundEntityException;
import com.softserve.osbb.service.exe.OnCreateEntityException;
import com.softserve.osbb.service.exe.OnDeleteEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazar.dovhyy on 09.07.2016.
 */
@Service
public class ReportServiceImpl implements ReportService {

    private static final List<Report> EMPTY_LIST = new ArrayList<>(0);

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report addReport(Report report) throws OnCreateEntityException {
        return report == null ? Report.EMPTY_REPORT : addReportIfNotExists(report);
    }

    private Report addReportIfNotExists(Report report) throws OnCreateEntityException {
        boolean isExisted = reportRepository.exists(report.getReportId());
        if (isExisted) {
            throw new OnCreateEntityException("report cannot be created under id: " + report.getReportId()
                    + "as already exists");
        }
        return reportRepository.save(report);
    }

    @Override
    public Report updateReport(Integer reportId, Report report) throws NotFoundEntityException {
        return report == null ? Report.EMPTY_REPORT : updateReportIfExists(reportId, report);
    }

    private Report updateReportIfExists(Integer reportId, Report report) throws NotFoundEntityException {
        boolean isExisted = reportRepository.exists(reportId);
        if (!isExisted) {
            throw new NotFoundEntityException("report under id: " + report.getReportId() + " doesn't exist and thus" +
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
                        .get() : Report.EMPTY_REPORT;
    }

    @Override
    public List<Report> getAlReportsBySearchParameter(String searchTerm) {
        return (searchTerm == null || searchTerm.isEmpty()) ? EMPTY_LIST :
                reportRepository.getAllReportsBySearchParam(searchTerm);
    }



    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public List<Report> findByDate(LocalDate dateToFind) {
        return (dateToFind == null)? EMPTY_LIST : reportRepository.findByCreationDate(dateToFind);
    }


    @Override
    public void deleteAll() {
        reportRepository.deleteAll();

    }

    @Override
    public void deleteReportById(Integer reportId) throws NotFoundEntityException {
        boolean isExistedReport = reportRepository.exists(reportId);
        if (!isExistedReport) {
            throw new NotFoundEntityException("the report with id: " + reportId + "" +
                    " cannot be deleted as wasnt found");
        }
        reportRepository.delete(reportId);
    }

    @Override
    public List<Report> getAllReportsBetweenDates(LocalDate from, LocalDate to) throws Exception {
        return reportRepository.gerAllReportsBetweenDates(from, to);
    }
}
