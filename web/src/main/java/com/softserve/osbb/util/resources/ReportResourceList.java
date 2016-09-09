package com.softserve.osbb.util.resources;

import com.softserve.osbb.controller.ReportController;
import com.softserve.osbb.dto.ReportDTO;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by nazar.dovhyy on 18.08.2016.
 */
public class ReportResourceList extends EntityResourceList<ReportDTO> {
    @Override
    public Resource<ReportDTO> createLink(Resource<ReportDTO> reportResource) {
        ReportDTO report = reportResource.getContent();
        reportResource.add(linkTo(methodOn(ReportController.class)
                .getReportById(report.getReportId())).withSelfRel());

        return reportResource;
    }
}
