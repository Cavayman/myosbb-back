package com.softserve.osbb.service;

import com.softserve.osbb.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    Report getReportById(Integer reportId) ;

    Report getOneReportBySearchTerm(String name) throws Exception;

    List<Report> getAlReportsBySearchParameter(String searchTerm) throws Exception;

    List<Report> getAllReportsByUserAndSearchParameter(Integer userId, String searchParam);

    Page<Report> getAllReports(PageRequest pageRequest);

    Page<Report> getAllUserReports(Integer userId, PageRequest pageRequest);

    List<Report> getAllReportsBetweenDates(LocalDate from, LocalDate to);

    List<Report> getAllUserReportsBetweenDates(Integer userId, LocalDate from, LocalDate to);

    List<Report> findByDate(LocalDate dateToFind);

    List<LocalDate> findDistinctCreationDates();

    void deleteAll() throws Exception;

    boolean deleteReportById(Integer reportId) throws Exception;

    List<Report> getAllReports();

}
