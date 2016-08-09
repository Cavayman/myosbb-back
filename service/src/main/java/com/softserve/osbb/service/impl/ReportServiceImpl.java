package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Report;
import com.softserve.osbb.repository.ReportRepository;
import com.softserve.osbb.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazar.dovhyy on 09.07.2016.
 */
@Service
public class ReportServiceImpl implements ReportService {

    private static final List<Report> EMPTY_LIST = new ArrayList<>(0);
    private static final int DEF_ROWS = 10;

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report addReport(Report report) {
        return report == null ? Report.NO_REPORT : addNotNullReport(report);
    }

    private Report addNotNullReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Report updateReport(Integer reportId, Report report) {
        return report == null ? Report.NO_REPORT : updateReportIfExists(reportId, report);
    }

    private Report updateReportIfExists(Integer reportId, Report report) {
        boolean isExisted = reportRepository.exists(reportId);
        if (!isExisted) {
            return Report.NO_REPORT;
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
                        .get() : Report.NO_REPORT;
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
        return (dateToFind == null) ? EMPTY_LIST : reportRepository.findByCreationDate(dateToFind);
    }

    @Override
    public List<LocalDate> findDistinctCreationDates() {
        return reportRepository.findDistinctCreationDates();
    }


    @Override
    public void deleteAll() {
        reportRepository.deleteAll();

    }

    @Override
    public boolean deleteReportById(Integer reportId) {
        boolean isExistedReport = reportRepository.exists(reportId);
        if (!isExistedReport) {
            return false;
        }
        reportRepository.delete(reportId);
        return true;
    }

    @Override
    public Page<Report> getAllReports(Integer pageNumber, String sortBy, Boolean order) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, DEF_ROWS,
                getSortingOrder(order), sortBy == null ? "creationDate" : sortBy);
        return reportRepository.findAll(pageRequest);
    }

    public Sort.Direction getSortingOrder(Boolean order) {
        if (order == null) {
            return Sort.Direction.DESC;
        }
        return order == true ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    @Override
    public List<Report> getAllReportsBetweenDates(LocalDate from, LocalDate to) {
        return reportRepository.getAllReportsBetweenDates(from, to);
    }


}
