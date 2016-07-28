package com.softserve.osbb.model.report;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
public final class ReportGenFactory {

    private static Map<String, ReportTemplate> reports = new HashMap<>();

    public static ReportTemplate generate(String type) {
        ReportTemplate reportTemplate = reports.get(type);

        if (reportTemplate == null) {
            switch (type.toLowerCase()) {
                case "pdf":
                    reports.put(type, new PdfReportTemplate());
                    break;
                case "xls":
                    reports.put(type, new XlsReportTemplate());
                    break;
                case "csv":
                    reports.put(type, new CsvReportTemplate());
                default:
                    return ReportTemplate.NULL;
            }
            return reports.get(type);
        }

        return reportTemplate;

    }
}
