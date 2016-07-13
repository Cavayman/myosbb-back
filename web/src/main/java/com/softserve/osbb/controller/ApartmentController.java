package com.softserve.osbb.controller;

import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 13.07.2016.
 */
@RestController
@RequestMapping("/restful")
public class ApartmentController {

    @Autowired
    ApartmentService apartmentService;

    @RequestMapping(value = "/apartments", method = RequestMethod.GET)
    public List<Apartment> getAllApartment() {

    return apartmentService.findAllApartment();

    }
    @RequestMapping(value = "/addapartment",method=RequestMethod.POST)
    public void addApartment (@RequestBody Apartment apartment){
        apartmentService.saveApartment(apartment);
    }
    @RequestMapping(value="apartment/{id}",method=RequestMethod.GET)
    public Apartment getAppartmentByNumber(@PathVariable ("id") Integer idApartment){
        return apartmentService.findOneApartmentByID(idApartment);
    }
    @RequestMapping(value="apartment/{id}",method=RequestMethod.DELETE)
    public void deleteAppartmentById(@PathVariable ("id") Integer appartmentId){
        apartmentService.deleteApartmentByID(appartmentId);
    }
    @RequestMapping(value="apartment/{id}",method = RequestMethod.PUT)
    public Apartment updateApartment(@PathVariable("id") Integer idAppartment, @RequestBody Apartment apartment){
        apartmentService.updateApartment(apartment);
        return apartment;
}

    }




