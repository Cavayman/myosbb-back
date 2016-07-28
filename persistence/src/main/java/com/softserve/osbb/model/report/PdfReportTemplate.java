package com.softserve.osbb.model.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import java.io.ByteArrayOutputStream;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
public class PdfReportTemplate extends ReportTemplate {
    @Override
    public void export(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
        this.fileName = "user_bill.pdf";
        JRPdfExporter jrPdfExporter = new JRPdfExporter();
        jrPdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
        jrPdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        jrPdfExporter.exportReport();
    }
}
