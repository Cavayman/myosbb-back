package com.softserve.osbb.controller;

import com.softserve.osbb.dto.ApartmentDTO;
import com.softserve.osbb.dto.mappers.ApartmentDTOMapper;
import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.service.ApartmentService;
import com.softserve.osbb.service.UserService;
import com.softserve.osbb.util.PageCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.softserve.osbb.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.softserve.osbb.util.ResourceUtil.toResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Oleg on 13.07.2016.
 */
@RestController
@CrossOrigin
@RequestMapping("/restful/apartment")
public class ApartmentController {

    @Autowired
    ApartmentService apartmentService;

    @Autowired
    UserService userService;
    private static Logger logger = LoggerFactory.getLogger(ApartmentController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Apartment>>> getAllApartment() {

        List<Apartment> apartmentsList = apartmentService.findAllApartment();
        logger.info("Getting all apartments.");
        List<Apartment> apartmentList = apartmentService.findAllApartment();
        final List<Resource<Apartment>> resourceApartmentList = new ArrayList<>();
        for (Apartment a : apartmentList) {
            resourceApartmentList.add(addResourceLinkToApartment(a));

        }
        return new ResponseEntity<>(resourceApartmentList, HttpStatus.OK);


    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<PageCreator<Resource<Apartment>>> getAllApartment(
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "asc", required = false) Boolean ascOrder) {
        logger.info("get all apartments by page number: " + pageNumber);
        Page<Apartment> apartmentByPage = apartmentService.getAllApartment(pageNumber, sortedBy, ascOrder);

        int currentPage = apartmentByPage.getNumber() + 1;
        int begin = Math.max(1, currentPage - 5);
        int totalPages = apartmentByPage.getTotalPages();
        int end = Math.min(currentPage + 5, totalPages);

        List<Resource<Apartment>> resourceList = new ArrayList<>();
        apartmentByPage.forEach((apartment) -> resourceList.add(getLink(toResource(apartment))));

        PageCreator<Resource<Apartment>> pageCreator = new PageCreator<>();
        pageCreator.setRows(resourceList);
        pageCreator.setCurrentPage(Integer.valueOf(currentPage).toString());
        pageCreator.setBeginPage(Integer.valueOf(begin).toString());
        pageCreator.setEndPage(Integer.valueOf(end).toString());
        pageCreator.setTotalPages(Integer.valueOf(totalPages).toString());

        return new ResponseEntity<>(pageCreator, HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Resource<Apartment>> addUsersToApartment(
            @PathVariable("id") Integer id, @RequestBody User user) {
        Apartment newApartment = apartmentService.findOneApartmentByID(id);
        user = userService.findOne(user.getUserId());
        if ((user.isOwner()) && (newApartment.getOwner() == null)) {
            newApartment.setOwner(user.getUserId());
        } else {
            logger.info("Owner already exist");
        }
        user.setApartment(newApartment);
        userService.saveAndFlush(user);


        logger.info("size of  list " + newApartment.getUsers().size());

        return new ResponseEntity<>(addResourceLinkToApartment(newApartment), HttpStatus.OK);
    }

    @RequestMapping(value = "/users{id}", method = RequestMethod.GET)

    public ResponseEntity<List<Resource<User>>> getUsersInApartment(@PathVariable("id") Integer id) {

        List<User> userList = apartmentService.findOneApartmentByID(id).getUsers();
        logger.info("Getting all users.");

        final List<Resource<User>> resourceUserList = new ArrayList<>();
        for (User u : userList) {
            resourceUserList.add(addResourceLinkToApartment(u));

        }
        return new ResponseEntity<>(resourceUserList, HttpStatus.OK);


    }




    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<Apartment>> addApartment(@RequestBody Apartment apartment) {
        logger.info("saving apartment" + apartment);
        apartmentService.saveApartment(apartment);
        return new ResponseEntity<>(addResourceLinkToApartment(apartment), HttpStatus.OK);

    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Apartment>> findApartmentById(@PathVariable("id") Integer id) {
        logger.info("getting apartment by id:" + id);
        Apartment apartment = apartmentService.findOneApartmentByID(id);
        return new ResponseEntity<>(addResourceLinkToApartment(apartment), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Resource<Apartment>> deleteAppartmentById(@PathVariable("id") Integer id) {
        logger.info("deleting ");
        apartmentService.deleteApartmentByID(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Apartment>> updateApartment(@RequestBody Apartment apartment) {
        logger.info("updating ");
        Apartment uApartment = apartmentService.updateApartment(apartment);
        return new ResponseEntity<>(addResourceLinkToApartment(uApartment), HttpStatus.OK);
    }

    private Resource<Apartment> addResourceLinkToApartment(Apartment apartment) {
        if (apartment == null) return null;
        Resource<Apartment> apartmentResource = new Resource<>(apartment);
        apartmentResource.add(linkTo(methodOn(ApartmentController.class)
                .findApartmentById(apartment.getApartmentId()))
                .withSelfRel());
        return apartmentResource;
    }

    private Resource<User> addResourceLinkToApartment(User user) {
        if (user == null) return null;
        Resource<User> apartmentResource = new Resource<>(user);
        apartmentResource.add(linkTo(methodOn(ApartmentController.class)
                .findApartmentById(user.getUserId()))
                .withSelfRel());
        return apartmentResource;
    }

    private Resource<Apartment> getLink(Resource<Apartment> apartmentResource) {
        apartmentResource.add(linkTo(methodOn(ApartmentController.class)
                .findApartmentById(apartmentResource.getContent().getApartmentId()))
                .withSelfRel());
        return apartmentResource;
    }


}




