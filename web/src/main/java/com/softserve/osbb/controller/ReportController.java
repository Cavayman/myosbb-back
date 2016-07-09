package com.softserve.osbb.controller;

import com.softserve.osbb.model.Report;
import com.softserve.osbb.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nazar.dovhyy on 09.07.2016.
 */
@RestController
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> listAllReports() {

        List<Report> reports = reportService.getAllReports();

        if (reports.isEmpty()) {
            System.out.println("empty list");
            return new ResponseEntity<List<Report>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Report>>(reports, HttpStatus.OK);
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ResponseEntity<Report> createReport(@RequestBody Report report) {

        try {
            report = reportService.addReport(report);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<Report>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<Report>(report, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
    public ResponseEntity<Report> getReportById(@PathVariable("id") Integer reportId) {
        Report report = reportService.getReportById(reportId);

        return report == null ? new ResponseEntity<Report>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<Report>(report, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Report> updateReport(@PathVariable("id") Integer reportId, @RequestBody Report report) {

        try {
            report = reportService.updateReport(report);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<Report>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Report>(report, HttpStatus.OK);
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getReportsByName(@RequestParam("searchParam") String searchParam) {

        List<Report> reportsBySearchTerm = reportService.getAllReportsBySearchTerm(searchParam);

        if (reportsBySearchTerm.isEmpty()) {
            System.out.println("no reports were found");
            return new ResponseEntity<List<Report>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Report>>(reportsBySearchTerm, HttpStatus.OK);
    }


}
