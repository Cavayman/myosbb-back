package com.softserve.osbb.controller;

import com.softserve.osbb.dto.ReportDTO;
import com.softserve.osbb.dto.mappers.ReportDTOMapper;
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

    private static final List<Resource<ReportDTO>> EMPTY_LIST = new ArrayList<>(0);
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
    public ResponseEntity<List<Resource<ReportDTO>>> listAllReports() {
        List<Report> reportList = reportService.getAllReports();
        logger.info("getting all reports: " + reportList);
        if (reportList.isEmpty()) {
            logger.warn("no reportList were found in the list: " + reportList);
            return new ResponseEntity<>(EMPTY_LIST, HttpStatus.OK);
        }
        final EntityResourceList<ReportDTO> reportResourceLinkList = new ReportResourceList();
        reportList.forEach((report) -> {
            ReportDTO reportDTO = ReportDTOMapper.mapReportEntityToDTO(report);
            reportResourceLinkList.add(toResource(reportDTO));
        });

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
        EntityResourceList<ReportDTO> reportResourceLinkList = new ReportResourceList();
        reportsByPage.forEach((report) -> {
            ReportDTO reportDTO = ReportDTOMapper.mapReportEntityToDTO(report);
            reportResourceLinkList.add(toResource(reportDTO));
        });
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
        EntityResourceList<ReportDTO> reportResourceLinkList = new ReportResourceList();
        reportsByPage.forEach((report) -> {
            ReportDTO reportDTO = ReportDTOMapper.mapReportEntityToDTO(report);
            reportResourceLinkList.add(toResource(reportDTO));
        });
        ReportPageCreator pageCreator = setUpPageCreator(pageSelector, reportResourceLinkList);
        return new ResponseEntity<>(pageCreator, HttpStatus.OK);
    }

    private ReportPageCreator setUpPageCreator(PageRequestGenerator.PageSelector pageSelector, List<Resource<ReportDTO>> resourceList) {
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
    public ResponseEntity<EntityResourceList<ReportDTO>> listReportsByDates(
            @RequestParam("dateFrom") String dateFrom,
            @RequestParam("dateTo") String dateTo
    ) {
        LocalDate localDateFrom = CustomLocalDateDeserializer.toLocalDateParse(dateFrom);
        LocalDate localDateTo = CustomLocalDateDeserializer.toLocalDateParse(dateTo);
        List<Report> reportList = reportService.getAllReportsBetweenDates(localDateFrom, localDateTo);
        EntityResourceList<ReportDTO> reportResourceLinkList = new ReportResourceList();
        reportList.forEach((report) -> {
            ReportDTO reportDTO = ReportDTOMapper.mapReportEntityToDTO(report);
            reportResourceLinkList.add(toResource(reportDTO));
        });

        return new ResponseEntity<>(reportResourceLinkList, HttpStatus.OK);

    }

    @RequestMapping(value = "/user/{userId}/between", method = RequestMethod.GET)
    public ResponseEntity<EntityResourceList<ReportDTO>> listUserReportsByDates(
            @PathVariable("userId") Integer userId,
            @RequestParam(value = "dateFrom", required = true) String dateFrom,
            @RequestParam(value = "dateTo", required = true) String dateTo
    ) {
        LocalDate localDateFrom = CustomLocalDateDeserializer.toLocalDateParse(dateFrom);
        LocalDate localDateTo = CustomLocalDateDeserializer.toLocalDateParse(dateTo);
        List<Report> userReportList = reportService.getAllUserReportsBetweenDates(userId, localDateFrom, localDateTo);
        EntityResourceList<ReportDTO> reportResourceLinkList = new ReportResourceList();
        userReportList.forEach((report) -> {
            ReportDTO reportDTO = ReportDTOMapper.mapReportEntityToDTO(report);
            reportResourceLinkList.add(toResource(reportDTO));
        });

        return new ResponseEntity<>(reportResourceLinkList, HttpStatus.OK);

    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Resource<ReportDTO>> createReport(@RequestBody ReportDTO reportDTO) {
        Resource<ReportDTO> reportResource;
        logger.info("saving report");
        Report report = ReportDTOMapper.mapDTOToReportEntity(reportDTO, reportService);
        report = reportService.addReport(report);
        if (report == null) {
            logger.warn("report wasn't saved");
            throw new ReportNotSavedException();
        }
        logger.info("fetching back: " + report.getReportId());
        ResourceLinkCreator<ReportDTO> reportResourceLinkCreator = new ReportResourceList();
        reportResource = reportResourceLinkCreator
                .createLink(toResource(ReportDTOMapper.mapReportEntityToDTO(report)));
        return new ResponseEntity<>(reportResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<ReportDTO>> getReportById(@PathVariable("id") Integer reportId) {
        logger.info("fetching reportResource by id: " + reportId);
        Report report = reportService.getReportById(reportId);
        if (report == null) {
            logger.error("report was not found");
            throw new ReportNotFoundException();
        }
        ResourceLinkCreator<ReportDTO> reportResourceLinkCreator = new ReportResourceList();
        Resource<ReportDTO> reportResource = reportResourceLinkCreator.createLink(toResource(ReportDTOMapper.mapReportEntityToDTO(report)));
        return new ResponseEntity<>(reportResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Resource<ReportDTO>> updateReport(@RequestBody ReportDTO reportDTO) {
        Resource<ReportDTO> reportResource;
        logger.info("updating report with id: " + reportDTO.getReportId());
        Report report = ReportDTOMapper.mapDTOToReportEntity(reportDTO, reportService);
        report = reportService.updateReport(report.getReportId(), report);
        if (report == null) {
            logger.error("report was not updated");
            throw new ReportNotFoundException();
        }
        ResourceLinkCreator<ReportDTO> reportResourceLinkCreator = new ReportResourceList();
        reportResource = reportResourceLinkCreator.createLink(toResource(ReportDTOMapper.mapReportEntityToDTO(report)));
        return new ResponseEntity<>(reportResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<ReportDTO>>> getReportsByName(@RequestParam(value = "searchParam")
                                                                              String searchParam) {
        logger.info("fetching reportResource by search parameter: " + searchParam);
        List<Report> reportsBySearchTerm = reportService.getAlReportsBySearchParameter(searchParam);
        if (reportsBySearchTerm.isEmpty()) {
            logger.warn("no reports were found");
            return new ResponseEntity<>(EMPTY_LIST, HttpStatus.OK);
        }
        EntityResourceList<ReportDTO> reportResourceLinkList = new ReportResourceList();
        reportsBySearchTerm.forEach((report) -> {
            ReportDTO reportDTO = ReportDTOMapper.mapReportEntityToDTO(report);
            reportResourceLinkList.add(toResource(reportDTO));
        });

        return new ResponseEntity<>(reportResourceLinkList, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}/find", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<ReportDTO>>> getUserReportsByName(
            @PathVariable("userId") Integer userId,
            @RequestParam(value = "searchParam") String searchParam) {
        logger.info(String.format("listing all reports for user: %d , by search value: %s ", userId, searchParam));
        List<Report> reportsBySearchTerm = reportService.getAllReportsByUserAndSearchParameter(userId, searchParam);
        if (reportsBySearchTerm.isEmpty()) {
            logger.warn("no reports were found");
            return new ResponseEntity<>(EMPTY_LIST, HttpStatus.OK);
        }
        EntityResourceList<ReportDTO> reportResourceLinkList = new ReportResourceList();
        reportsBySearchTerm.forEach((report) -> {
            ReportDTO reportDTO = ReportDTOMapper.mapReportEntityToDTO(report);
            reportResourceLinkList.add(toResource(reportDTO));
        });
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
        logger.info("preparing generate");
        reportDownloadService.generate(type, httpServletResponse);

    }

    @RequestMapping(value = "/user/{userId}/download", method = RequestMethod.GET)
    public void download(@PathVariable("userId") Integer userId,
                         @RequestParam(value = "type", required = true) String type,
                         HttpServletResponse httpServletResponse) {
        logger.info("preparing generate for userId: " + userId);
        User currentUser = userService.findOne(userId);
        reportDownloadService.generate(currentUser, type, httpServletResponse);

    }

    @RequestMapping(value = "/{reportId}/download", method = RequestMethod.GET)
    public void downloadExisting(@PathVariable("reportId") Integer reportId,
                         HttpServletResponse httpServletResponse) {
        logger.info("preparing download report with id: " + reportId);
        reportDownloadService.downloadExisting(reportId, httpServletResponse);

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Report not found")
    private class ReportNotFoundException extends RuntimeException {
    }

    @ResponseStatus(value = HttpStatus.NOT_MODIFIED, reason = "Report not saved")
    private class ReportNotSavedException extends RuntimeException {
    }
}
