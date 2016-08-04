package com.softserve.osbb.controller;

import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.service.ApartmentService;
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
 * Created by Oleg on 13.07.2016.
 */
@RestController
@RequestMapping("/restful/apartment")
public class ApartmentController {

    @Autowired
    ApartmentService apartmentService;
    private static Logger logger = LoggerFactory.getLogger(ApartmentController.class);
    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Apartment>>>  getAllApartment() {

        List<Apartment> apartmentsList = apartmentService.findAllApartment();
        logger.info("Getting all apartments.");
     List<Apartment> apartmentList= apartmentService.findAllApartment();
        final List<Resource<Apartment>> resourceApartmentList= new ArrayList<>();
        for (Apartment a:apartmentList) {
            resourceApartmentList.add(addResourceLinkToApartment(a));

        }
        return new ResponseEntity<>(resourceApartmentList, HttpStatus.OK);


    }
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Resource<Apartment>> addApartment (@RequestBody Apartment apartment){
        logger.info("saving apartment"+apartment);
       apartmentService.saveApartment(apartment);
        return  new ResponseEntity<>(addResourceLinkToApartment(apartment),HttpStatus.OK);

    }

    @RequestMapping(value="/number{number}",method=RequestMethod.GET)
    public ResponseEntity<Resource<Apartment>> getAppartmentByNumber(@PathVariable ("number") Integer number){
      Apartment apartment=apartmentService.findApartmentByNumber(number);
        logger.info("finding apartment...");
        return new ResponseEntity<>(addResourceLinkToApartment(apartment),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Resource<Apartment>> findApartmentById(@PathVariable("id") Integer id){
        logger.info("getting apartment by id:"+id);
        Apartment apartment=apartmentService.findOneApartmentByID(id);
        return new ResponseEntity<>(addResourceLinkToApartment(apartment),HttpStatus.OK);
    }

    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Resource<Apartment>> deleteAppartmentById(@PathVariable ("id") Integer id){
        logger.info("deleting ");
        apartmentService.deleteApartmentByID(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @RequestMapping(value="",method = RequestMethod.PUT)
    public ResponseEntity<Resource<Apartment>> updateApartment(@PathVariable("id") Integer idAppartment, @RequestBody Apartment apartment) {
        logger.info("updating ");
        Apartment uApartment= apartmentService.updateApartment(apartment);
        return new ResponseEntity<>(addResourceLinkToApartment(apartment),HttpStatus.OK);
    }
private Resource<Apartment> addResourceLinkToApartment(Apartment apartment){
    if (apartment == null) return null;
    Resource<Apartment> apartmentResource = new Resource<>(apartment);
    apartmentResource.add(linkTo(methodOn(ApartmentController.class)
            .findApartmentById(apartment.getApartmentId()))
            .withSelfRel());
    return apartmentResource;
}

    }




