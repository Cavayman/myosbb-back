package com.softserve.osbb.model.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
public class CsvReportSaver extends ReportSaver {

    public CsvReportSaver() {
        this.setFileName(buildDestinationFileName(getFileExtension()));
    }

    @Override
    public void saveToOutputStream(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
        JRCsvExporter exporterCSV = new JRCsvExporter();
        exporterCSV.setParameter(JRCsvExporterParameter.JASPER_PRINT, jp);
        exporterCSV.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, baos);
        exporterCSV.exportReport();
    }

    @Override
    public String saveToFile(JasperPrint jasperPrint, String outputDir) throws JRException {
        final String fileName = getFileName();
        JRCsvExporter exporter = new JRCsvExporter();
        String destFileName = outputDir + File.separator + fileName;
        exporter.setParameter(JRExporterParameter.JASPER_PRINT,
                jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                destFileName);
        exporter.exportReport();

        return fileName;
    }
}
