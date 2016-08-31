package com.softserve.osbb.service.utils;

import com.softserve.osbb.model.Report;
import com.softserve.osbb.model.User;
import com.softserve.osbb.repository.ReportRepository;
import com.softserve.osbb.utils.Constants;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
@Service
public class ReportDownloadService {

    protected static Logger logger = LoggerFactory.getLogger(ReportDownloadService.class);

    private static final String ALL_USERS_REPORT_TEMPLATE = "/reportTemplate/report_admin_template.jrxml";
    private static final String SINGLE_USER_REPORT_TEMPLATE = "/reportTemplate/report_user_template.jrxml";

    @Value("${app.filepath}")
    private String serverDir;

    @Autowired
    private ReportCreator reportCreator;

    @Autowired
    private ReportExporterService reportExporterService;

    @Autowired
    private ReportRepository reportRepository;

    private User currentUser;

    public void generate(String type, HttpServletResponse httpServletResponse) {
        try {
            Map<String, Object> invoiceParam = generateReportTemplateParams(reportNumberGenerator);
            JasperPrint jp = generateReportTemplate(invoiceParam);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            String savedFilePath = reportExporterService.export(type, jp, httpServletResponse, buffer);
            saveReportToDatabase(savedFilePath, invoiceParam);
            write(httpServletResponse, buffer);
        } catch (JRException | IOException e) {
            logger.error("unable to process generate");
            throw new RuntimeException(e);
        }
    }

    public void generate(User currentUser, String type, HttpServletResponse httpServletResponse) {
        this.currentUser = new User(currentUser);
        try {
            Map<String, Object> invoiceParam = generateCurrentUserReportTemplateParams(reportNumberGenerator);
            JasperPrint jp = generateCurrentUserReportTemplate(invoiceParam);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            String savedFilePath = reportExporterService.export(type, jp, httpServletResponse, buffer);
            saveReportToDatabase(savedFilePath, invoiceParam);
            write(httpServletResponse, buffer);
        } catch (JRException | IOException e) {
            logger.error("unable to process generate");
            throw new RuntimeException(e);
        }
    }


    private JasperPrint generateReportTemplate(Map<String, Object> reportTemplateParams) throws JRException {
        InputStream is = this.getClass().getResourceAsStream(ALL_USERS_REPORT_TEMPLATE);
        JasperDesign jd = JRXmlLoader.load(is);
        JasperReport jr = JasperCompileManager.compileReport(jd);
        JRDataSource dataSource = reportCreator.getReportModelListDataSource();
        return JasperFillManager.fillReport(jr, reportTemplateParams, dataSource);
    }

    private JasperPrint generateCurrentUserReportTemplate(Map<String, Object> currentUserReportTemplateParams) throws JRException {
        InputStream is = this.getClass().getResourceAsStream(SINGLE_USER_REPORT_TEMPLATE);
        JasperDesign jd = JRXmlLoader.load(is);
        JasperReport jr = JasperCompileManager.compileReport(jd);
        JRDataSource dataSource = reportCreator.getReportModelListDataSource(currentUser);
        return JasperFillManager.fillReport(jr, currentUserReportTemplateParams, dataSource);
    }

    private static ReportNumberGenerator reportNumberGenerator = new ReportNumberGenerator() {
        private long counter;

        @Override
        public String generate() {
            counter = System.currentTimeMillis();
            long hours = TimeUnit.MILLISECONDS.toHours(counter) -
                    TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(counter));
            long minutes = TimeUnit.MILLISECONDS.toMinutes(counter) -
                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(counter));
            long seconds = TimeUnit.MILLISECONDS.toSeconds(counter) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(counter));
            StringBuilder invoiceGenStringBuilder = new StringBuilder();
            invoiceGenStringBuilder.append(Constants.DATE_FORMATTER.format(new Date()))
                    .append("-")
                    .append(String.format("%02d%02d%02d", hours, minutes, seconds));
            return invoiceGenStringBuilder.toString();
        }
    };

    private Map<String, Object> generateReportTemplateParams(ReportNumberGenerator reportNumberGenerator) {
        if (reportNumberGenerator == null) {
            throw new IllegalArgumentException();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("Title", "User Report");
        params.put("invoiceNumber", reportNumberGenerator.generate());
        params.put("invoiceDate", new Date());
        return params;
    }

    private Map<String, Object> generateCurrentUserReportTemplateParams(ReportNumberGenerator reportNumberGenerator) {

        if (reportNumberGenerator == null) {
            throw new IllegalArgumentException();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("customerName", currentUser.getFirstName() + " " + currentUser.getLastName());
        params.put("customerEmail", currentUser.getEmail());
        params.put("invoiceNumber", reportNumberGenerator.generate());
        params.put("invoiceDate", new Date());
        return params;
    }

    private void saveReportToDatabase(String savedFileName, Map<String, Object> invoiceParam) {
        Report report = new Report();
        report.setName((String) invoiceParam.get("invoiceNumber"));
        report.setCreationDate(LocalDate.now());
        report.setFilePath(Constants.REPORTS_DIR_NAME + File.separator + Constants.DATE_FORMATTER.format(new Date()) + File.separator + savedFileName);
        report.setUser(currentUser);
        reportRepository.save(report);
        logger.info("saved report :" + report.getName() + " to database");
    }

    private void write(HttpServletResponse response, ByteArrayOutputStream baos) {
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            logger.info("writing to output stream");
            baos.writeTo(servletOutputStream);
            baos.flush();
        } catch (IOException e) {
            logger.error("unable to write report to the output stream");
            throw new RuntimeException(e);
        }

    }

    public void downloadExisting(Integer reportId, HttpServletResponse httpServletResponse) {
        logger.info("fetching existing report from db");
        Report report = reportRepository.findOne(reportId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Path fileDownloadPath = null;
        try {
            fileDownloadPath = Paths.get(report.getFilePath());
            Files.copy(fileDownloadPath, byteArrayOutputStream);
            write(httpServletResponse, byteArrayOutputStream);
        } catch (IOException e) {
            logger.error("could not read from input stream for " + fileDownloadPath.toAbsolutePath());
            throw new RuntimeException(e);
        }

    }
}
