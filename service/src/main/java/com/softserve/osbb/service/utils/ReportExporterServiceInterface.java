package com.softserve.osbb.service.utils;

import net.sf.jasperreports.engine.JasperPrint;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by nazar.dovhyy on 28.08.2016.
 */
public interface ReportExporterServiceInterface {

    String export(String type, JasperPrint jp, HttpServletResponse response, ByteArrayOutputStream baos) throws IOException;

}
