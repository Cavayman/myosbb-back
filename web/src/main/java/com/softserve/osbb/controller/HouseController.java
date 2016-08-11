package com.softserve.osbb.controller;

import com.softserve.osbb.dto.HouseDTO;
import com.softserve.osbb.dto.HouseDTOMapper;
import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.House;
import com.softserve.osbb.service.HouseService;
import com.softserve.osbb.util.PageCreator;
import com.softserve.osbb.util.resources.ApartmentResourceList;
import com.softserve.osbb.util.resources.EntityResourceList;
import com.softserve.osbb.util.resources.HouseResourceList;
import com.softserve.osbb.util.resources.ResourceLinkCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<EntityResourceList<HouseDTO>> listAllHouses() {
//        final EntityResourceList<HouseDTO> houseDTOEntityResourceList = new HouseResourceList();
//        List<House> houseList = houseService.getAllHouses();
//        logger.info("retrieve all houses: " + houseList);
//        houseList.forEach((house) -> {
//            HouseDTO houseDTO = HouseDTOMapper.mapHouseEntityToDTO(house);
//            Resource<HouseDTO> housePageDTOResource = houseDTOEntityResourceList.createLink(toResource(houseDTO));
//            houseDTOEntityResourceList.add(housePageDTOResource);
//        });
//
//        return new ResponseEntity<>(houseDTOEntityResourceList, HttpStatus.OK);
//    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageCreator<Resource<HouseDTO>>> listAllHouses(
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "order", required = false) Boolean order,
            @RequestParam(value = "rowNum", required = false) Integer rowNum) {
        logger.info("get all houses by page number: " + pageNumber);
        Page<House> housesByPage = houseService.getAllHouses(pageNumber, sortedBy, order, rowNum);

        int currentPage = housesByPage.getNumber() + 1;
        int begin = Math.max(1, currentPage - 5);
        int totalPages = housesByPage.getTotalPages();
        int end = Math.min(currentPage + 5, totalPages);

        List<HouseDTO> houseDTOs = new ArrayList<>();
        EntityResourceList<HouseDTO> houseDTOEntityResourceList = new HouseResourceList();

        housesByPage.forEach(house -> {
                    HouseDTO houseDTO = HouseDTOMapper.mapHouseEntityToDTO(house);
                    houseDTOEntityResourceList
                            .add(toResource(houseDTO));
                }
        );
        PageCreator<Resource<HouseDTO>> houseDTOPageCreator = new PageCreator<>();
        houseDTOPageCreator.setRows(houseDTOEntityResourceList);
        houseDTOPageCreator.setCurrentPage(Integer.valueOf(currentPage).toString());
        houseDTOPageCreator.setBeginPage(Integer.valueOf(begin).toString());
        houseDTOPageCreator.setEndPage(Integer.valueOf(end).toString());
        houseDTOPageCreator.setTotalPages(Integer.valueOf(totalPages).toString());

        return new ResponseEntity<>(houseDTOPageCreator, HttpStatus.OK);
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
            e.printStackTrace();
        }

        return new ResponseEntity<>(resourceApartmentList, HttpStatus.OK);
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
            e.printStackTrace();
        }
        return new ResponseEntity(houseResource, HttpStatus.OK);
    }

    /*
    @RequestMapping(value = "", method = RequestMethod.POST)
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
*/

}
