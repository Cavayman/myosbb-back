package com.softserve.osbb.controller;

import com.softserve.osbb.model.Staff;
import com.softserve.osbb.service.StaffService;
import com.softserve.osbb.util.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Anastasiia Fedorak on 12.07.2016.
 */
@RestController
@RequestMapping(value = "/restful/staff")
public class StaffController {
    private static Logger logger = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    StaffService staffService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Staff>>> listAllStaffs() {
        List<Staff> staffList = staffService.findAllStaffs();
        logger.info("getting all staffs: " + staffList);
        final List<Resource<Staff>> resourceStaffList = new ArrayList<>();
        staffList.stream().forEach((staff) -> {
            try {
                resourceStaffList.add(addResourceLinkToStaff(staff));
            } catch (EntityNotFoundException e) {
                logger.error(e.getMessage());
            }
        });
        return new ResponseEntity<>(resourceStaffList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Staff>> getStaffById(@PathVariable("id") Integer staffId) {
        logger.info("fetching staff by id: " + staffId);
        Staff staff = staffService.findOneStaffById(staffId);
        Resource<Staff> staffResource = null;
        try {
            staffResource = addResourceLinkToStaff(staff);
        } catch (EntityNotFoundException e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(staffResource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<Staff>> createStaff(@RequestBody Staff staff) {
        Resource<Staff> staffResource;
        try {
            logger.info("saving staff object " + staff);
            staffService.saveStaff(staff);
            staffResource = addResourceLinkToStaff(staff);
        } catch (EntityNotFoundException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(staffResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Staff>> updateStaff(@PathVariable("id") Integer staffId,
                                                             @RequestBody Staff staff) {

        Resource<Staff> staffResource = null;
        try {
            logger.info("updating staff by id: " + staffId);
            staff = staffService.updateStaff(staffId, staff);
            staffResource = addResourceLinkToStaff(staff);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(staffResource, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Staff> deleteStaffById(@PathVariable("id") Integer staffId){
        logger.info("removing staff by id: " + staffId);
       if (staffService.existsStaff(staffId)){
           staffService.deleteStaffById(staffId);
       }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Staff> deleteAllStaffs() {
        logger.info("removing all staffs");
        staffService.deleteAllStaffs();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Resource<Staff> addResourceLinkToStaff(Staff staff) throws EntityNotFoundException {
        if (staff == null) throw new EntityNotFoundException();
        Resource<Staff> staffResource = new Resource<>(staff);
        staffResource.add(linkTo(methodOn(StaffController.class)
                .getStaffById(staff.getStaffId()))
                .withSelfRel());
        return staffResource;
    }
}
