package com.softserve.osbb.model.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.ByteArrayOutputStream;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
public abstract class ReportTemplate {

    public static final ReportTemplate NULL = null;

    protected String fileName;

    public abstract void export(JasperPrint jp, ByteArrayOutputStream baos) throws JRException;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
