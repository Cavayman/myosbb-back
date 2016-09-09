package com.softserve.osbb.dto.mappers;

import com.softserve.osbb.dto.ReportDTO;
import com.softserve.osbb.model.Report;
import com.softserve.osbb.service.ReportService;

/**
 * Created by nazar.dovhyy on 28.08.2016.
 */
public class ReportDTOMapper {

    public static ReportDTO mapReportEntityToDTO(Report report) {
        ReportDTO reportDTO = null;
        if (report != null) {
            reportDTO = new ReportDTO.ReportDTOBuilder()
                    .setReportId(report.getReportId())
                    .setName(report.getName())
                    .setCreationDate(report.getCreationDate())
                    .setDescription(report.getDescription())
                    .setFilePath(report.getFilePath())
                    .setUserId(report.getUser())
                    .build();
        }

        return reportDTO;
    }


    public static Report mapDTOToReportEntity(ReportDTO reportDTO, ReportService reportService) {
        Report report = null;
        if (reportDTO != null) {
            if (reportDTO.getReportId() != null) {
                if (reportService == null) {
                    throw new IllegalArgumentException("no instance of reportService provided");
                }
                report = reportService.getReportById(reportDTO.getReportId());
                mapReportDTOToReport(reportDTO, report);
                return report;
            }

            report = new Report();
            mapReportDTOToReport(reportDTO, report);
        }

        return report;
    }

    private static void mapReportDTOToReport(ReportDTO reportDTO, Report report) {
        report.setName(reportDTO.getName());
        report.setDescription(reportDTO.getDescription());
        report.setCreationDate(reportDTO.getCreationDate());
    }
}
