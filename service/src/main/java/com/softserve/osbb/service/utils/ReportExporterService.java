package com.softserve.osbb.service.utils;

import com.softserve.osbb.model.report.ReportGenFactory;
import com.softserve.osbb.model.report.ReportSaver;
import com.softserve.osbb.utils.Constants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
@Service
class ReportExporterService implements ReportExporterServiceInterface {

    private static Logger logger = LoggerFactory.getLogger(ReportExporterService.class);

    @Autowired
    private FileServer fileServer;

    @Override
    public String export(String type, JasperPrint jp, HttpServletResponse response, ByteArrayOutputStream baos) throws IOException {
        exportToOutputStream(type, jp, response, baos);
        return exportToFileSystem(jp, type, fileServer.getOutputFileDirectory(Constants.REPORTS_DIR_NAME));
    }

    private HttpServletResponse exportToOutputStream(String type, JasperPrint jp, HttpServletResponse response, ByteArrayOutputStream baos) {
        ReportSaver reportSaver = ReportGenFactory.generate(type);
        try {
            if (reportSaver != null) {
                reportSaver.saveToOutputStream(jp, baos);
                logger.info("exporting report output stream in " + type);
                buildHttpServletResponseMessage(response, baos, reportSaver.getFileName());
            }
        } catch (JRException e) {
            logger.error("failed to save report in " + type);
            throw new RuntimeException(e);
        }

        return response;
    }

    private String exportToFileSystem(JasperPrint jp, String type, String outputDir) {
        String destFileName = null;
        ReportSaver reportSaver = ReportGenFactory.generate(type);
        if (reportSaver != null) {
            try {
                destFileName = reportSaver.saveToFile(jp, outputDir);
                logger.info("exporting report to file in " + type);
            } catch (JRException e) {
                logger.error("failed to save report to file in " + type);
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("reportSaver wasn't generated due to wrong type: " + type);
        }

        return destFileName;
    }


    private void buildHttpServletResponseMessage(HttpServletResponse response, ByteArrayOutputStream baos, String fileName) {
        response.setHeader("Content-disposition", ", inline; fileName= " + fileName);
        response.setContentType(HttpResponseMessageBuilder.returnContentTypeOf(fileName));
        response.setContentLength(baos.size());
    }


}
