package com.softserve.osbb.controller;

import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Oleg on 13.07.2016.
 */
@RestController
@RequestMapping("/restful/apartment")
public class ApartmentController {

    @Autowired
    ApartmentService apartmentService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Apartment> getAllApartment() {

    return apartmentService.findAllApartment();

    }
    @RequestMapping(value = "/add",method=RequestMethod.POST)
    public void addApartment (@RequestBody Apartment apartment){
        apartmentService.saveApartment(apartment);
    }

    @RequestMapping(value="/number{number}",method=RequestMethod.GET)
    public Apartment getAppartmentByNumber(@PathVariable ("number") Integer number){
       //return apartmentService.findApartmentByNumber(number);
        return apartmentService.findOneApartmentByID(number);
    }
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public void deleteAppartmentById(@PathVariable ("id") Integer appartmentId){
        apartmentService.deleteApartmentByID(appartmentId);
    }
    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public Apartment updateApartment(@PathVariable("id") Integer idAppartment, @RequestBody Apartment apartment){
        apartmentService.saveApartment(apartment);
        return apartment;
}

    }




