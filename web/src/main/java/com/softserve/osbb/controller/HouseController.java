package com.softserve.osbb.controller;

import com.softserve.osbb.dto.HouseDTO;
import com.softserve.osbb.dto.mappers.HouseDTOMapper;
import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.House;
import com.softserve.osbb.service.HouseService;
import com.softserve.osbb.util.PageCreator;
import com.softserve.osbb.util.PageRequestGenerator;
import com.softserve.osbb.util.resources.ApartmentResourceList;
import com.softserve.osbb.util.resources.EntityResourceList;
import com.softserve.osbb.util.resources.HouseResourceList;
import com.softserve.osbb.util.resources.ResourceLinkCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@CrossOrigin()
@RequestMapping("/restful/house")
public class HouseController {

    @Autowired
    HouseService houseService;
    private static Logger logger = LoggerFactory.getLogger(HouseController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageCreator<Resource<HouseDTO>>> listAllHouses(
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "order", required = false) Boolean order,
            @RequestParam(value = "rowNum", required = false) Integer rowNum) {
        logger.info("get all houses by page number: " + pageNumber);
        final PageRequest pageRequest = PageRequestGenerator.generatePageRequest(pageNumber)
                .addSortedBy(sortedBy, "street")
                .addOrderType(order)
                .addRows(rowNum)
                .toPageRequest();

        Page<House> housesByPage = houseService.getAllHouses(pageRequest);
        PageRequestGenerator.PageSelector pageSelector = PageRequestGenerator.generatePageSelectorData(housesByPage);
        EntityResourceList<HouseDTO> houseDTOEntityResourceList = new HouseResourceList();
        housesByPage.forEach(house -> {
                    HouseDTO houseDTO = HouseDTOMapper.mapHouseEntityToDTO(house);
            logger.info("houseDTO created " + houseDTO.toString());
            houseDTOEntityResourceList.add(toResource(houseDTO));
                }
        );
        PageCreator<Resource<HouseDTO>> houseDTOPageCreator = setUpPageCreator(pageSelector, houseDTOEntityResourceList);

        return new ResponseEntity<>(houseDTOPageCreator, HttpStatus.OK);
    }

    private PageCreator<Resource<HouseDTO>> setUpPageCreator(PageRequestGenerator.PageSelector pageSelector, EntityResourceList<HouseDTO> houseDTOEntityResourceList) {
        PageCreator<Resource<HouseDTO>> houseDTOPageCreator = new PageCreator<>();
        houseDTOPageCreator.setRows(houseDTOEntityResourceList);
        houseDTOPageCreator.setCurrentPage(Integer.valueOf(pageSelector.getCurrentPage()).toString());
        houseDTOPageCreator.setBeginPage(Integer.valueOf(pageSelector.getBegin()).toString());
        houseDTOPageCreator.setEndPage(Integer.valueOf(pageSelector.getEnd()).toString());
        houseDTOPageCreator.setTotalPages(Integer.valueOf(pageSelector.getTotalPages()).toString());
        return houseDTOPageCreator;
    }

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
            throw new HouseNotFoundException(e);
        }

        return new ResponseEntity<>(resourceApartmentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<EntityResourceList<HouseDTO>> findAllHousesByName(
            @RequestParam(value = "searchParam", required = true) String searchParam
    ) {
        logger.info("searching by searchParam ", searchParam);
        final EntityResourceList<HouseDTO> houseDTOEntityResourceList = new HouseResourceList();
        List<House> houseList = houseService.getAllHousesBySearchParameter(searchParam);
        houseList.forEach((house) -> {
            houseDTOEntityResourceList.add(toResource(HouseDTOMapper.mapHouseEntityToDTO(house)));
        });

        return new ResponseEntity<>(houseDTOEntityResourceList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<HouseDTO>> getHouseById(@PathVariable("id") Integer houseId) {
        House house;
        HouseDTO houseDTO;
        Resource<HouseDTO> houseResource = null;
        try {
            house = houseService.findHouseById(houseId);
            houseDTO = HouseDTOMapper.mapHouseEntityToDTO(house);
            ResourceLinkCreator<HouseDTO> houseResourceLinkCreator = new HouseResourceList();
            houseResource = houseResourceLinkCreator.createLink(toResource(houseDTO));
        } catch (Exception e) {
            logger.error("error finding house by id: ", houseId);
            throw new HouseNotFoundException(e);
        }
        return new ResponseEntity(houseResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{houseId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteHouseById(@PathVariable("houseId") Integer houseId) {
        logger.info("deleting house by id: " + houseId);
        final boolean isDeleted = houseService.deleteHouseById(houseId);
        if (!isDeleted) {
            logger.warn("no house was found by id: " + houseId);
            throw new HouseNotFoundException();
        }
        logger.info("successfully deleted house with id: " + houseId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "House not found")
    private class HouseNotFoundException extends RuntimeException {

        public HouseNotFoundException() {
        }

        public HouseNotFoundException(Exception e) {
            super(e);
        }
    }
}
