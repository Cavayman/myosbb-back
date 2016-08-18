package com.softserve.osbb.util.resources;

import com.softserve.osbb.controller.OsbbController;
import com.softserve.osbb.controller.ReportController;
import com.softserve.osbb.controller.UserController;
import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.model.Report;
import com.softserve.osbb.model.User;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by nazar.dovhyy on 18.08.2016.
 */
public class ReportResourceList extends EntityResourceList<Report> {
    @Override
    public Resource<Report> createLink(Resource<Report> reportResource) {
        Report report = reportResource.getContent();

        reportResource.add(linkTo(methodOn(ReportController.class)
                .getReportById(report.getReportId())).withSelfRel());

        User reportOwner = reportResource.getContent().getUser();

        if (reportOwner != null) {
            reportResource.add(linkTo(methodOn(UserController.class)
                    .getUser(reportOwner.getUserId().toString()))
                    .withRel("user"));
        }

        return reportResource;
    }
}
