package com.softserve.osbb.model.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import java.io.ByteArrayOutputStream;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
public class CsvReportTemplate extends ReportTemplate {
    @Override
    public void export(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
        this.setFileName("user_bill.csv");
        JRCsvExporter exporterCSV = new JRCsvExporter();
        exporterCSV.setParameter(JRXlsExporterParameter.JASPER_PRINT, jp);
        exporterCSV.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos);
        exporterCSV.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, true);
        exporterCSV.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
        exporterCSV.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, true);
        exporterCSV.exportReport();
    }
}
