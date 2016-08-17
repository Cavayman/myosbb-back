package com.softserve.osbb.service.gen;

import com.softserve.osbb.model.Report;
import com.softserve.osbb.model.User;
import com.softserve.osbb.repository.ReportRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
@Service
public class InvoiceDownloadService {

    protected static Logger logger = LoggerFactory.getLogger(InvoiceDownloadService.class);
    private static final String TEMPLATE_ALL = "/reportTemplate/invoice_all.jrxml";
    private static final String TEMPLATE_CURRENT = "/reportTemplate/invoice_single.jrxml";

    @Autowired
    private InvoiceCreator invoiceCreator;

    @Autowired
    private InvoiceExporterService invoiceExporterService;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private DropBoxFileServer dropBoxFileServer;

    private User currentUser;


    public void download(String type, HttpServletResponse httpServletResponse) {
        try {
            Map<String, Object> invoiceParam = generateInvoiceParam(invoiceNumberGenerator);
            JasperPrint jp = generateInvoiceTemplate(invoiceParam);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            invoiceExporterService.exportToOutputStream(type, jp, httpServletResponse, baos);
            String invoiceFileName = invoiceExporterService.exportToFile(jp, type, createServerFileFolder());
            saveFileToDataBase(invoiceFileName, invoiceParam);
            write(httpServletResponse, baos, type);
        } catch (JRException e) {
            logger.error("unable to process download");
            throw new RuntimeException(e);
        }

    }

    public void downloadFor(User currentUser, String type, HttpServletResponse httpServletResponse) {
        this.currentUser = new User(currentUser);
        try {
            Map<String, Object> invoiceParam = generateCurrentUserInvoiceParam(invoiceNumberGenerator);
            JasperPrint jp = generateCurrentUserInvoiceTemplate(invoiceParam);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            invoiceExporterService.exportToOutputStream(type, jp, httpServletResponse, baos);
            String invoiceFileName = invoiceExporterService.exportToFile(jp, type, createServerFileFolder());
            saveFileToDataBase(invoiceFileName, invoiceParam);
            write(httpServletResponse, baos, type);
        } catch (JRException e) {
            logger.error("unable to process download");
            throw new RuntimeException(e);
        }
    }


    private JasperPrint generateInvoiceTemplate(Map<String, Object> invoiceParam) throws JRException {
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE_ALL);
        JasperDesign jd = JRXmlLoader.load(is);
        JasperReport jr = JasperCompileManager.compileReport(jd);
        JRDataSource dataSource = invoiceCreator.getInvoiceModelList();
        return JasperFillManager.fillReport(jr, invoiceParam, dataSource);
    }

    private JasperPrint generateCurrentUserInvoiceTemplate(Map<String, Object> currentUserInvoiceParam) throws JRException {
        InputStream is = this.getClass().getResourceAsStream(TEMPLATE_CURRENT);
        JasperDesign jd = JRXmlLoader.load(is);
        JasperReport jr = JasperCompileManager.compileReport(jd);
        JRDataSource dataSource = invoiceCreator.getCurrentUserInvoiceModelList(currentUser);
        return JasperFillManager.fillReport(jr, currentUserInvoiceParam, dataSource);
    }

    private static InvoiceNumberGenerator invoiceNumberGenerator = new InvoiceNumberGenerator() {
        private int counter = 1000;
        private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public String generate() {
            StringBuilder invoiceGenStringBuilder = new StringBuilder();
            invoiceGenStringBuilder.append(dateFormatter.format(new Date()))
                    .append("-")
                    .append(counter++);
            return invoiceGenStringBuilder.toString();
        }
    };

    private Map<String, Object> generateInvoiceParam(InvoiceNumberGenerator invoiceNumberGenerator) {

        if (invoiceNumberGenerator == null) {
            throw new IllegalArgumentException();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("Title", "User Report");
        params.put("invoiceNumber", invoiceNumberGenerator.generate());
        params.put("invoiceDate", new Date());

        return params;
    }

    private Map<String, Object> generateCurrentUserInvoiceParam(InvoiceNumberGenerator invoiceNumberGenerator) {

        if (invoiceNumberGenerator == null) {
            throw new IllegalArgumentException();
        }
        Map<String, Object> params = new HashMap();
        params.put("customerName", currentUser.getFirstName() + " " + currentUser.getLastName());
        params.put("customerEmail", currentUser.getEmail());
        params.put("invoiceNumber", invoiceNumberGenerator.generate());
        params.put("invoiceDate", new Date());

        return params;
    }


    public String createServerFileFolder() {
        String serverDir = System.getProperty("user.home");
        File outputFileDir = new File(serverDir + File.separator + "reports");
        if (!outputFileDir.exists()) {
            outputFileDir.mkdir();
        }
        String outputFilePath = outputFileDir.getAbsolutePath();
        logger.info("save file to server directory: " + outputFilePath);
        return outputFilePath;
    }

    private void saveFileToDataBase(String fileName, Map<String, Object> invoiceParam) {
        Report report = new Report();
        report.setName((String) invoiceParam.get("invoiceNumber"));
        report.setCreationDate(LocalDate.now());
        report.setFilePath("http://localhost" + "/" + fileName);
        if (currentUser != null) {
            report.setUser(currentUser);
        }
        reportRepository.save(report);
        logger.info("saved report :" + report.getName() + " to database");
    }

    private void write(HttpServletResponse response, ByteArrayOutputStream baos, String type) {
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            logger.info("writing to output stream");
            baos.writeTo(servletOutputStream);
            baos.flush();
        } catch (IOException e) {
            logger.error("unable to write report to the output stream");
            e.printStackTrace();
        }

    }

}
