package com.softserve.osbb.model.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
public class PdfReportSaver extends ReportSaver {

    public PdfReportSaver() {
        this.setFileName(buildDestinationFileName(getFileExtension()));
    }

    @Override
    public void saveToOutputStream(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
        JRPdfExporter jrPdfExporter = new JRPdfExporter();
        jrPdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
        jrPdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        jrPdfExporter.exportReport();
    }

    @Override
    public String saveToFile(JasperPrint jasperPrint, String outputDir) throws JRException {
        final String fileName = getFileName();
        String outputFileName = outputDir + File.separator + fileName;
        if (jasperPrint != null) {
            JRPdfExporter jrException = new JRPdfExporter();
            jrException.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            jrException.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputFileName);
            jrException.exportReport();
        }
        return fileName;
    }


}
