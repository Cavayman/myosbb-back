package com.softserve.osbb.controller;

import com.softserve.osbb.model.Report;
import com.softserve.osbb.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by nazar.dovhyy on 09.07.2016.
 */
@RestController
public class ReportController {

    private static final Resource<Report> EMPTY_REPORT_LINK = new Resource<>(new Report());

    @Autowired
    private ReportServiceImpl reportService;

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Report>>> listAllReports() {

        List<Report> reportList = reportService.getAllReports();

        if (reportList.isEmpty()) {
            System.out.println("no reportList were found in the list");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        final List<Resource<Report>> resourceReportList = new CopyOnWriteArrayList<>();

        reportList.stream().forEach((report) -> resourceReportList.add(addResourceLinkToReport(report)));

        return new ResponseEntity<>(resourceReportList, HttpStatus.OK);
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ResponseEntity<Resource<Report>> createReport(@RequestBody Report report) {

        Resource<Report> reportResource;
        try {
            report = reportService.addReport(report);

            reportResource = addResourceLinkToReport(report);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(reportResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Report>> getReportById(@PathVariable("id") Integer reportId) {

        Report report = reportService.getReportById(reportId);

        Resource<Report> reportResource = addResourceLinkToReport(report);

        return reportResource == EMPTY_REPORT_LINK ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(reportResource, HttpStatus.OK);
    }

    private Resource<Report> addResourceLinkToReport(Report report) {

        if (report == null) {
            return EMPTY_REPORT_LINK;
        }

        Resource<Report> reportResource = new Resource<>(report);

        reportResource.add(linkTo(methodOn(ReportController.class)
                .getReportById(report.getReportId()))
                .withSelfRel());
        return reportResource;
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Report>> updateReport(@PathVariable("id") Integer reportId, @RequestBody Report report) {

        Resource<Report> reportResource;

        try {
            report = reportService.updateReport(reportId, report);

            reportResource = addResourceLinkToReport(report);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reportResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/find", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Report>>> getReportsByName(@RequestParam(value = "searchParam", required = true)
                                                                   String searchParam) {

        List<Report> reportsBySearchTerm = reportService.getAllReportsBySearchTerm(searchParam);

        if (reportsBySearchTerm.isEmpty()) {
            System.out.println("no reports were found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Resource<Report>> resourceReportList = new CopyOnWriteArrayList<>();

        reportsBySearchTerm.stream().forEach((report) -> resourceReportList.add(addResourceLinkToReport(report)));

        return new ResponseEntity<>(resourceReportList, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Report> deleteReportById(@PathVariable("id") Integer reportId) {
        reportService.deleteReportById(reportId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/report", method = RequestMethod.DELETE)
    public ResponseEntity<Report> deleteAllReports() {
        reportService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
