package com.softserve.osbb.controller;

import com.softserve.osbb.model.Report;
import com.softserve.osbb.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nazar.dovhyy on 09.07.2016.
 */
@RestController
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;

    @RequestMapping(value = "/get/", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> listAllReports() {

        List<Report> reports = reportService.getAllReports();

        if (reports.isEmpty()) {
            System.out.println("empty list");
            return new ResponseEntity<List<Report>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Report>>(reports, HttpStatus.OK);
    }


}
