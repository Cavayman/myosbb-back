package com.softserve.osbb.model.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
public class XlsReportSaver extends ReportSaver {

    public XlsReportSaver() {
        setFileName(buildDestinationFileName(getFileExtension()));
    }

    @Override
    public void saveToOutputStream(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
        JRXlsExporter jrXlsExporter = new JRXlsExporter();
        jrXlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        jrXlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
        jrXlsExporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXlsExporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);
        jrXlsExporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        jrXlsExporter.exportReport();
    }

    @Override
    public String saveToFile(JasperPrint jasperPrint, String outputDir) throws JRException {
        final String fileName = getFileName();
        String destFileName = outputDir + File.separator + fileName;
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT,
                jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                destFileName);
        exporter.exportReport();
        return fileName;
    }
}
