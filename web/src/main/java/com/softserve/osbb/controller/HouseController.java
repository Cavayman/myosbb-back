package com.softserve.osbb.controller;

import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.House;
import com.softserve.osbb.service.HouseService;
import com.softserve.osbb.util.resources.ApartmentResourceList;
import com.softserve.osbb.util.resources.EntityResourceList;
import com.softserve.osbb.util.resources.HouseResourceList;
import com.softserve.osbb.util.resources.ResourceLinkCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.softserve.osbb.util.ResourceUtil.toResource;

/**
 * Created by nazar.dovhyy on 19.07.2016.
 */
@RestController
@RequestMapping("/restful/house")
public class HouseController {

    @Autowired
    HouseService houseService;
    private static Logger logger = LoggerFactory.getLogger(HouseController.class);

    @RequestMapping(value = "/{id}/apartments", method = RequestMethod.GET)
    public ResponseEntity<EntityResourceList<Apartment>> getAllApartmentsByHouseId(@PathVariable("id") Integer houseId) {
        final House house;
        final EntityResourceList<Apartment> resourceApartmentList =
                new ApartmentResourceList();
        try {
            house = houseService.findHouseById(houseId);
            List<Apartment> apartmentList = (List<Apartment>) house.getApartments();
            apartmentList.forEach((apartment) -> {
                Resource<Apartment> apartmentLink = resourceApartmentList
                        .createLink(toResource(apartment));
                resourceApartmentList.add(apartmentLink);
            });

        } catch (Exception e) {
            logger.error("error finding house by id: ", houseId);
            e.printStackTrace();
        }

        return new ResponseEntity<>(resourceApartmentList, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<House>> getHouseById(@PathVariable("id") Integer houseId) {
        House house;
        Resource<House> houseResource = null;
        try {
            house = houseService.findHouseById(houseId);
            ResourceLinkCreator<House> houseResourceLinkCreator = new HouseResourceList();
            houseResource = houseResourceLinkCreator.createLink(toResource(house));

        } catch (Exception e) {
            logger.error("error finding house by id: ",  houseId);
            e.printStackTrace();
        }
        return new ResponseEntity<>(houseResource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<House>> saveHouse(@RequestBody House house) {
        Resource<House> houseResource = null;
        try {
            house = houseService.addHouse(house);
            ResourceLinkCreator<House> houseResourceLinkCreator = new HouseResourceList();
            houseResource = houseResourceLinkCreator.createLink(toResource(house));
        } catch (Exception e) {
            logger.error("error saving object", house);
            e.printStackTrace();
        }
        return new ResponseEntity<>(houseResource, HttpStatus.OK);
    }


}
