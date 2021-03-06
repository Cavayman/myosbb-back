package com.softserve.osbb.util.resources;

import com.softserve.osbb.controller.HouseController;
import com.softserve.osbb.dto.HouseDTO;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by nazar.dovhyy on 19.07.2016.
 */
public class HouseResourceList extends EntityResourceList<HouseDTO> {

    @Override
    public Resource<HouseDTO> createLink(Resource<HouseDTO> houseResource) {
        HouseDTO house = houseResource.getContent();

        houseResource.add(linkTo(methodOn(HouseController.class)
                .getHouseById(house.getHouseId()))
                .withSelfRel());

        /*houseResource.add(linkTo(methodOn(HouseController.class)
                .getAllApartmentsByHouseId(house.getHouseId()))
                .withRel("apartments"));
        */
        return houseResource;
    }
}
