package com.softserve.osbb.model.report;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
public final class ReportGenFactory {

    private static Map<String, ReportSaver> reports = new HashMap<>();

    public static ReportSaver generate(String type) {
        ReportSaver reportSaver = reports.get(type);

        if (reportSaver == null) {
            switch (type.toLowerCase()) {
                case "pdf":
                    reports.put(type, new PdfReportSaver());
                    break;
                case "xls":
                    reports.put(type, new XlsReportSaver());
                    break;
                case "csv":
                    reports.put(type, new CsvReportSaver());
                default:
                    return ReportSaver.NULL;
            }
            return reports.get(type);
        }

        return reportSaver;

    }
}
