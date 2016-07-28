package com.softserve.osbb.service.gen;

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
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
@Service
public class ReportDownloadService {

    protected static Logger logger = LoggerFactory.getLogger(ReportDownloadService.class);
    public static final String TEMPLATE = "/application.properties";

    @Autowired
    private ReportCreatorDataSource reportCreatorDataSource;

    @Autowired
    private ReportGeneratorService reportGeneratorService;


    public void download(String type, HttpServletResponse response) {

        try {
            // add report parameters
            Map<String, Object> params = new HashMap<>();
            params.put("Title", "User Report");
            //retrieve template
            InputStream is = this.getClass().getResourceAsStream(TEMPLATE);
            JasperDesign jd = JRXmlLoader.load(is);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, reportCreatorDataSource.getDataSource());
            //create stream where the object will be written
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            reportGeneratorService.export(type, jp, response, baos);
            //write to response
            write(response, baos);
        } catch (JRException e) {
            logger.error("unable to process download");
            e.printStackTrace();
        }

    }

    //write report to the output stream
    private void write(HttpServletResponse response, ByteArrayOutputStream baos) {

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
