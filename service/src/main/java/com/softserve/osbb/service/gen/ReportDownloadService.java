package com.softserve.osbb.service.gen;

import com.softserve.osbb.model.Report;
import com.softserve.osbb.repository.ReportRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
@Service
public class ReportDownloadService {

    protected static Logger logger = LoggerFactory.getLogger(ReportDownloadService.class);
    public static final String TEMPLATE = "/reportTemplate/report.jrxml";

    @Autowired
    private ReportCreatorDataSource reportCreatorDataSource;

    @Autowired
    private ReportExporterService reportExporterService;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    ServletContext servletContext;


    public void download(String type, HttpServletResponse response) {

        try {
            // add report parameters
            Map<String, Object> params = new HashMap<>();
            params.put("Title", "User Report");
            //retrieve template
            InputStream is = this.getClass().getResourceAsStream(TEMPLATE);
            JasperDesign jd = JRXmlLoader.load(is);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JRDataSource dataSource = reportCreatorDataSource.getDataSource();
            JasperPrint jp = JasperFillManager.fillReport(jr, params, dataSource);
            //create stream where the object will be written
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            reportExporterService.exportToOutputStream(type, jp, response, baos);

            String outputDir = "C:\\" + "reports"; // temporary solution
            File outputFileDir = new File(outputDir);
            if (!outputFileDir.exists()) {
                outputFileDir.mkdir();
            }
            logger.info("save file directory: " + outputDir);
            String filePath = reportExporterService.exportToFile(jp, type, outputDir);

            saveFileToDataBase(filePath);

            //write to response
            write(response, baos, type);
        } catch (JRException e) {
            logger.error("unable to process download");
            e.printStackTrace();
        }

    }

    private void saveFileToDataBase(String fileName) {
        Report report = new Report();
        report.setName("report " + UUID.randomUUID().toString());
        report.setCreationDate(LocalDate.now());
        report.setFilePath(fileName);
        reportRepository.save(report);
        logger.info("saved report :" + report.getName() + " to database");
    }

    //write report to the output stream
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
