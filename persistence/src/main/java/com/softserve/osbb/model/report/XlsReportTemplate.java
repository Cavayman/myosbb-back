package com.softserve.osbb.model.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import java.io.ByteArrayOutputStream;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
public class XlsReportTemplate extends ReportTemplate {
    @Override
    public void export(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
        this.fileName = "user_bill.xls";
        // create jasper to xls instance object
        JRXlsExporter jrXlsExporter = new JRXlsExporter();
        //passing required parameters
        jrXlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        jrXlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
        //passing excel specific parameters
        jrXlsExporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXlsExporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXlsExporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        jrXlsExporter.exportReport();
    }
}
