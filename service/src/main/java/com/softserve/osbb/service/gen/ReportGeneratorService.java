package com.softserve.osbb.service.gen;

import com.softserve.osbb.model.report.ReportGenFactory;
import com.softserve.osbb.model.report.ReportTemplate;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
@Service
public class ReportGeneratorService {

    public static final String MEDIA_TYPE_EXCEL = "application/vnd.ms-excel";
    public static final String MEDIA_TYPE_PDF = "application/pdf";
    public static final String MEDIA_TYPE_CSV = "text/csv";

    private static Logger logger = LoggerFactory.getLogger(ReportGeneratorService.class);


    public HttpServletResponse export(String type,
                                      JasperPrint jp,
                                      HttpServletResponse response,
                                      ByteArrayOutputStream baos) {
        ReportTemplate reportTemplate = ReportGenFactory.generate(type);
        try {
            if (reportTemplate != null) {
                reportTemplate.export(jp, baos);
                logger.info("exporting report in " + type);
                buildResponse(response, baos, reportTemplate.getFileName());
            }
        } catch (JRException e) {
            logger.error("failed to export report in pdf");
            throw new RuntimeException(e);
        }

        return response;
    }

    private void buildResponse(HttpServletResponse response, ByteArrayOutputStream baos, String fileName) {
        response.setHeader("Content-disposition", ", inline; fileName= " + fileName);
        response.setContentType(setContentType(fileName));
        response.setContentLength(baos.size());
    }

    private String setContentType(String fileName) {
        switch (fileName.substring(fileName.lastIndexOf(".") + 1)) {
            case "pdf":
                return MEDIA_TYPE_PDF;
            case "xls":
                return MEDIA_TYPE_EXCEL;
            case "csv":
                return MEDIA_TYPE_CSV;
            default:
                return "application/json";
        }

    }

}
