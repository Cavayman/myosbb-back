package com.softserve.osbb.model.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
public abstract class ReportTemplate {

    public static final ReportTemplate NULL = null;

    private String fileName;

    public abstract void saveToOutputStream(JasperPrint jp, ByteArrayOutputStream baos) throws JRException;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public abstract String saveToFile(JasperPrint jasperPrint, String outputDir) throws JRException;

    protected String getFileExtension() {
        return this.getClass().getSimpleName().toLowerCase().substring(0, 3);
    }

    public String buildDestinationFileName(String type) {
        StringBuilder stringBuilder = new StringBuilder();
        String randomFileName = UUID.randomUUID().toString();
        stringBuilder.append("report" + randomFileName).append(".").append(type);
        return stringBuilder.toString();
    }

}
