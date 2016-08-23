package com.softserve.osbb.controller;

import com.softserve.osbb.model.Report;
import com.softserve.osbb.model.User;
import com.softserve.osbb.repository.ApartmentRepository;
import com.softserve.osbb.service.UserService;
import com.softserve.osbb.service.impl.ReportServiceImpl;
import com.softserve.osbb.service.utils.ReportDownloadService;
import com.softserve.osbb.util.PageRequestGenerator;
import com.softserve.osbb.util.ReportPageCreator;
import com.softserve.osbb.util.resources.EntityResourceList;
import com.softserve.osbb.util.resources.ReportResourceList;
import com.softserve.osbb.util.resources.ResourceLinkCreator;
import com.softserve.osbb.utils.CustomLocalDateDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.softserve.osbb.util.ResourceUtil.toResource;


/**
 * Created by nazar.dovhyy on 09.07.2016.
 */
@RestController
@CrossOrigin
@RequestMapping("/restful/report")
public class ReportController {

    private static final List<Resource<Report>> EMPTY_LIST = new ArrayList<>(0);
    private static Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportServiceImpl reportService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ReportDownloadService reportDownloadService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Report>>> listAllReports() {
        List<Report> reportList = reportService.getAllReports();
        logger.info("getting all reports: " + reportList);
        if (reportList.isEmpty()) {
            logger.warn("no reportList were found in the list: " + reportList);
            return new ResponseEntity<>(EMPTY_LIST, HttpStatus.OK);
        }
        final EntityResourceList<Report> reportResourceLinkList = new ReportResourceList();
        reportList.forEach((report) -> reportResourceLinkList.add(toResource(report)));
        return new ResponseEntity<>(reportResourceLinkList, HttpStatus.OK);
    }


    @RequestMapping(value = "/user/{userId}/all", method = RequestMethod.GET)
    public ResponseEntity<ReportPageCreator> listAllUserReports(
            @PathVariable("userId") Integer userId,
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "order", required = false) Boolean orderType,
            @RequestParam(value = "rowNum", required = false) Integer rowNum
    ) {
        logger.info(String.format("listing all reports for user: %d , page number: %d ", userId, pageNumber));
        final PageRequest pageRequest = PageRequestGenerator.generatePageRequest(pageNumber)
                .addRows(rowNum)
                .addOrderType(orderType)
                .addSortedBy(sortedBy, "name")
                .toPageRequest();
        Page<Report> reportsByPage = reportService.getAllUserReports(userId, pageRequest);
        PageRequestGenerator.PageSelector pageSelector = PageRequestGenerator
                .generatePageSelectorData(reportsByPage);
        EntityResourceList<Report> reportResourceLinkList = new ReportResourceList();
        reportsByPage.forEach((report) -> reportResourceLinkList.add(toResource(report)));
        ReportPageCreator pageCreator = setUpPageCreator(pageSelector, reportResourceLinkList);
        return new ResponseEntity<>(pageCreator, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ReportPageCreator> listAllReports(
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "order", required = false) Boolean orderType,
            @RequestParam(value = "rowNum", required = false) Integer rowNum) {
        logger.info("get all report by page number: " + pageNumber);
        final PageRequest pageRequest = PageRequestGenerator.generatePageRequest(pageNumber)
                .addRows(rowNum)
                .addSortedBy(sortedBy, "name")
                .addOrderType(orderType)
                .toPageRequest();
        Page<Report> reportsByPage = reportService.getAllReports(pageRequest);
        PageRequestGenerator.PageSelector pageSelector = PageRequestGenerator.generatePageSelectorData(reportsByPage);
        EntityResourceList<Report> reportResourceLinkList = new ReportResourceList();
        reportsByPage.forEach((report) -> reportResourceLinkList.add(toResource(report)));
        ReportPageCreator pageCreator = setUpPageCreator(pageSelector, reportResourceLinkList);
        return new ResponseEntity<>(pageCreator, HttpStatus.OK);
    }

    private ReportPageCreator setUpPageCreator(PageRequestGenerator.PageSelector pageSelector, List<Resource<Report>> resourceList) {
        ReportPageCreator pageCreator = new ReportPageCreator();
        pageCreator.setRows(resourceList);
        pageCreator.setCurrentPage(Integer.valueOf(pageSelector.getCurrentPage()).toString());
        pageCreator.setBeginPage(Integer.valueOf(pageSelector.getBegin()).toString());
        pageCreator.setEndPage(Integer.valueOf(pageSelector.getEnd()).toString());
        pageCreator.setTotalPages(Integer.valueOf(pageSelector.getTotalPages()).toString());
        pageCreator.setDates(reportService.findDistinctCreationDates());
        return pageCreator;
    }

    @RequestMapping(value = "/between", method = RequestMethod.GET)
    public ResponseEntity<EntityResourceList<Report>> listReportsByDates(
            @RequestParam("dateFrom") String dateFrom,
            @RequestParam("dateTo") String dateTo
    ) {
        LocalDate localDateFrom = CustomLocalDateDeserializer.toLocalDateParse(dateFrom);
        LocalDate localDateTo = CustomLocalDateDeserializer.toLocalDateParse(dateTo);
        List<Report> reportList = reportService.getAllReportsBetweenDates(localDateFrom, localDateTo);
        EntityResourceList<Report> reportResourceLinkList = new ReportResourceList();
        reportList.forEach((report) -> reportResourceLinkList.add(toResource(report)));
        return new ResponseEntity<>(reportResourceLinkList, HttpStatus.OK);

    }

    @RequestMapping(value = "/user/{userId}/between", method = RequestMethod.GET)
    public ResponseEntity<EntityResourceList<Report>> listUserReportsByDates(
            @PathVariable("userId") Integer userId,
            @RequestParam(value = "dateFrom", required = true) String dateFrom,
            @RequestParam(value = "dateTo", required = true) String dateTo
    ) {
        LocalDate localDateFrom = CustomLocalDateDeserializer.toLocalDateParse(dateFrom);
        LocalDate localDateTo = CustomLocalDateDeserializer.toLocalDateParse(dateTo);
        List<Report> userReportList = reportService.getAllUserReportsBetweenDates(userId, localDateFrom, localDateTo);
        EntityResourceList<Report> reportResourceLinkList = new ReportResourceList();
        userReportList.forEach((report) -> reportResourceLinkList.add(toResource(report)));
        return new ResponseEntity<>(reportResourceLinkList, HttpStatus.OK);

    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Resource<Report>> createReport(@RequestBody Report report) {
        Resource<Report> reportResource;
        logger.info("saving report ");
        report = reportService.addReport(report);
        logger.info("fetching back: " + report.getReportId());
        ResourceLinkCreator<Report> reportResourceLinkCreator = new ReportResourceList();
        reportResource = reportResourceLinkCreator.createLink(toResource(report));
        return new ResponseEntity<>(reportResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Report>> getReportById(@PathVariable("id") Integer reportId) {
        logger.info("fetching reportResource by id: " + reportId);
        Report report = reportService.getReportById(reportId);
        if (report == null) {
            logger.error("report was not found with id" + report.getReportId());
            throw new ReportNotFoundException();
        }
        ResourceLinkCreator<Report> reportResourceLinkCreator = new ReportResourceList();
        Resource<Report> reportResource = reportResourceLinkCreator.createLink(toResource(report));
        return new ResponseEntity<>(reportResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Report>> updateReport(@RequestBody Report report) {
        Resource<Report> reportResource;
        logger.info("updating reportResource by id: " + report.getReportId());
        report = reportService.updateReport(report.getReportId(), report);
        if (report == null) {
            logger.error("report was not deleted with id" + report.getReportId());
            throw new ReportNotFoundException();
        }
        ResourceLinkCreator<Report> reportResourceLinkCreator = new ReportResourceList();
        reportResource = reportResourceLinkCreator.createLink(toResource(report));
        return new ResponseEntity<>(reportResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Report>>> getReportsByName(@RequestParam(value = "searchParam",
            required = true) String searchParam) {
        logger.info("fetching reportResource by search parameter: " + searchParam);
        List<Report> reportsBySearchTerm = reportService.getAlReportsBySearchParameter(searchParam);
        if (reportsBySearchTerm.isEmpty()) {
            logger.warn("no reports were found");
            return new ResponseEntity<>(EMPTY_LIST, HttpStatus.OK);
        }
        EntityResourceList<Report> reportResourceLinkList = new ReportResourceList();
        reportsBySearchTerm.forEach((report) -> reportResourceLinkList.add(toResource(report)));
        return new ResponseEntity<>(reportResourceLinkList, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}/find", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Report>>> getUserReportsByName(
            @PathVariable("userId") Integer userId,
            @RequestParam(value = "searchParam",
                    required = true) String searchParam) {
        logger.info(String.format("listing all reports for user: %d , by search value: %s ", userId, searchParam));
        List<Report> reportsBySearchTerm = reportService.getAllReportsByUserAndSearchParameter(userId, searchParam);
        if (reportsBySearchTerm.isEmpty()) {
            logger.warn("no reports were found");
            return new ResponseEntity<>(EMPTY_LIST, HttpStatus.OK);
        }
        EntityResourceList<Report> reportResourceLinkList = new ReportResourceList();
        reportsBySearchTerm.forEach((report) -> reportResourceLinkList.add(toResource(report)));
        return new ResponseEntity<>(reportResourceLinkList, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Resource<Report>> deleteReportById(@PathVariable("id") Integer reportId) {
        logger.info("removing reportResource by id: " + reportId);
        final boolean isDeletedReport = reportService.deleteReportById(reportId);
        if (!isDeletedReport) {
            logger.warn("report with id: " + reportId + " wasn't deleted as not existed");
            throw new ReportNotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<Report> deleteAllReports() {
        logger.info("removing all reports");
        reportService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(@RequestParam(value = "type", required = true) String type,
                         HttpServletResponse httpServletResponse) {
        logger.info("preparing download");
        reportDownloadService.download(type, httpServletResponse);

    }

    @RequestMapping(value = "/user/{userId}/download", method = RequestMethod.GET)
    public void download(@PathVariable("userId") Integer userId,
                         @RequestParam(value = "type", required = true) String type,
                         HttpServletResponse httpServletResponse) {
        logger.info("preparing download for userId: " + userId);
        User currentUser = userService.findOne(userId);
        if (apartmentRepository.findByUser(currentUser) != null) {
            reportDownloadService.download(currentUser, type, httpServletResponse);
        } else {
            reportDownloadService.download(type, httpServletResponse);
        }

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Report not found")
    private class ReportNotFoundException extends RuntimeException {
    }

}
