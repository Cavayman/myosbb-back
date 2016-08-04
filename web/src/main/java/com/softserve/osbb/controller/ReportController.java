package com.softserve.osbb.controller;

import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.model.Report;
import com.softserve.osbb.service.gen.ReportDownloadService;
import com.softserve.osbb.service.impl.ReportServiceImpl;
import com.softserve.osbb.util.PageCreator;
import com.softserve.osbb.util.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static com.softserve.osbb.util.ResourceUtil.toResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
    private ReportDownloadService reportDownloadService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Report>>> listAllReports() {
        List<Report> reportList = reportService.getAllReports();
        logger.info("getting all reports: " + reportList);
        if (reportList.isEmpty()) {
            logger.warn("no reportList were found in the list: " + reportList);
            return new ResponseEntity<>(EMPTY_LIST, HttpStatus.OK);
        }
        final List<Resource<Report>> resourceReportList = new ArrayList<>();
        reportList.stream().forEach((report) -> resourceReportList.add(getLink(toResource(report))));
        return new ResponseEntity<>(resourceReportList, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<PageCreator<Resource<Report>>> listAllReports(
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "order", required = false) Boolean order) {
        logger.info("get all report by page number: " + pageNumber);
        Page<Report> reportsByPage = reportService.getAllReports(pageNumber, sortedBy, order);

        int currentPage = reportsByPage.getNumber() + 1;
        int begin = Math.max(1, currentPage - 5);
        int totalPages = reportsByPage.getTotalPages();
        int end = Math.min(currentPage + 5, totalPages);

        List<Resource<Report>> resourceList = new ArrayList<>();
        reportsByPage.forEach((report) -> resourceList.add(getLink(toResource(report))));

        PageCreator<Resource<Report>> pageCreator = new PageCreator<>();
        pageCreator.setRows(resourceList);
        pageCreator.setCurrentPage(Integer.valueOf(currentPage).toString());
        pageCreator.setBeginPage(Integer.valueOf(begin).toString());
        pageCreator.setEndPage(Integer.valueOf(end).toString());
        pageCreator.setTotalPages(Integer.valueOf(totalPages).toString());

        return new ResponseEntity<>(pageCreator, HttpStatus.OK);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Resource<Report>> createReport(@RequestBody Report report) {
        Resource<Report> reportResource;
        logger.info("saving reportResource object " + report);
        report = reportService.addReport(report);
        logger.info("fetching back: " + report);
        reportResource = getLink(toResource(report));
        return new ResponseEntity<>(reportResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Report>> getReportById(@PathVariable("id") Integer reportId) {
        logger.info("fetching reportResource by id: " + reportId);
        Report report = reportService.getReportById(reportId);
        Resource<Report> reportResource = getLink(toResource(report));
        return new ResponseEntity<>(reportResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Report>> updateReport(@RequestBody Report report) {
        Resource<Report> reportResource;
        logger.info("updating reportResource by id: " + report.getReportId());
        report = reportService.updateReport(report.getReportId(), report);
        reportResource = getLink(toResource(report));
        return new ResponseEntity<>(reportResource, HttpStatus.OK);
    }

    private Resource<Report> getLink(Resource<Report> reportResource) {
        //adding self-link
        reportResource.add(linkTo(methodOn(ReportController.class)
                .getReportById(reportResource.getContent().getReportId()))
                .withSelfRel());
        //adding link to osbb
        final Osbb osbbFromResource = reportResource.getContent().getOsbb();
        if (osbbFromResource != null) {
            reportResource.add(linkTo(methodOn(OsbbController.class)
                    .getOsbbById(osbbFromResource
                            .getOsbbId())).withRel("osbb"));
        }
        return reportResource;
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
        List<Resource<Report>> resourceReportList = new ArrayList<>();
        reportsBySearchTerm.stream().forEach((report) -> resourceReportList.add(getLink(toResource(report))));
        return new ResponseEntity<>(resourceReportList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Resource<Report>> deleteReportById(@PathVariable("id") Integer reportId) {
        logger.info("removing reportResource by id: " + reportId);
        final boolean isDeletedReport = reportService.deleteReportById(reportId);
        if (!isDeletedReport) {
            logger.warn("report with id: " + reportId + " wasn't deleted as not existed");
            throw new ResourceNotFoundException();
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

}
