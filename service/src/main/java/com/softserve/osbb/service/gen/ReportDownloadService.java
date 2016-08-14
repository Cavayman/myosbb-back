package com.softserve.osbb.service.gen;

import com.dropbox.core.DbxException;
import com.softserve.osbb.model.Report;
import com.softserve.osbb.repository.ReportRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
@Service
public class ReportDownloadService {

    protected static Logger logger = LoggerFactory.getLogger(ReportDownloadService.class);
    public static final String TEMPLATE = "/reportTemplate/invoice_all_report.jrxml";

    @Autowired
    private InvoiceCreator invoiceCreator;

    @Autowired
    private ReportExporterService reportExporterService;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private DropBoxFileServer dropBoxFileServer;


    public void download(String type, HttpServletResponse response) {

        try {
            JasperPrint jp = getJasperPrint();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            reportExporterService.exportToOutputStream(type, jp, response, baos);
            String filePath = reportExporterService.exportToFile(jp, type, createServerFileFolder());
            String sharedFilePathUrl = uploadToFileServer(filePath, type);
            saveFileToDataBase(sharedFilePathUrl);
            write(response, baos, type);
        } catch (JRException e) {
            logger.error("unable to process download");
            e.printStackTrace();
        }

    }

    private String uploadToFileServer(String filePath, String type) {
        String sharedFilePathUrl = "";
        logger.info("start uploadToFileServer()");
        try {
            try {
                dropBoxFileServer.authenticate();
            } catch (DbxException dbx) {
                logger.error("authentication error");
                dbx.printStackTrace();
            }
            try {
                sharedFilePathUrl = dropBoxFileServer.uploadFile(filePath, type);
            } catch (DbxException dbx1) {
                logger.error("unable to process file upload ");
                dbx1.printStackTrace();
            }
        } catch (IOException e) {
            logger.error("unable to process file " + filePath);
            e.printStackTrace();
        }
        return sharedFilePathUrl;
    }

    private JasperPrint getJasperPrint() throws JRException {
        Map<String, Object> params = new HashMap<>();
        params.put("Title", "User Report");
        params.put("invoiceNumber", "2016-08-14" + UUID.randomUUID().toString().substring(0, 5));
        params.put("invoiceDate", new Date());
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE);
        JasperDesign jd = JRXmlLoader.load(is);
        JasperReport jr = JasperCompileManager.compileReport(jd);
        JRDataSource dataSource = invoiceCreator.getDataSource();
        return JasperFillManager.fillReport(jr, params, dataSource);
    }

    public String createServerFileFolder() {
        String serverDir = System.getProperty("catalina.home");
        File outputFileDir = new File(serverDir + File.separator + "reports");
        if (!outputFileDir.exists()) {
            outputFileDir.mkdir();
        }
        String outputFilePath = outputFileDir.getAbsolutePath();
        logger.info("save file to server directory: " + outputFilePath);
        return outputFilePath;
    }

    private void saveFileToDataBase(String fileName) {
        Report report = new Report();
        report.setName("report " + UUID.randomUUID().toString());
        report.setCreationDate(LocalDate.now());
        report.setFilePath(fileName);
        reportRepository.save(report);
        logger.info("saved report :" + report.getName() + " to database");
    }

    private void write(HttpServletResponse response, ByteArrayOutputStream baos, String type) {
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            logger.info("writing to output stream");
            baos.writeTo(servletOutputStream);
            baos.flush();
        } catch (IOException e) {
            logger.error("unable to write report to the output stream");
            e.printStackTrace();
        }

    }

}
