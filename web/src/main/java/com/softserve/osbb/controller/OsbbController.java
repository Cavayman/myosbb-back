package com.softserve.osbb.controller;

import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.service.OsbbService;
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
 * Created by Roman on 12.07.2016.
 */

@RestController
@CrossOrigin
@RequestMapping("/restful")
public class OsbbController {

    private static Logger logger = LoggerFactory.getLogger(OsbbController.class);

    @Autowired
    private OsbbService osbbService;

    @RequestMapping(value = "/osbb", method = RequestMethod.POST)
    public ResponseEntity<Resource<Osbb>> createOsbb(@RequestBody Osbb osbb) {
        logger.info("Create osbb.  " + osbb);
        osbb = osbbService.addOsbb(osbb);
        return new ResponseEntity<>(addResourceLinkToOsbb(osbb), HttpStatus.OK);
    }

    @RequestMapping(value = "/osbb/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Osbb>> getOsbbById(@PathVariable("id") Integer osbbId) {
        logger.info("Get one osbb by id: " + osbbId);
        Osbb osbb = osbbService.getOsbb(osbbId);
        return new ResponseEntity<>(addResourceLinkToOsbb(osbb), HttpStatus.OK);
    }

    @RequestMapping(value = "/osbb/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Osbb>>> getOsbbByNameContaining(@PathVariable("name") String osbbName) {
        logger.info("Get all osbb: " +  osbbName);
        List<Osbb> osbbList = osbbService.findByNameContaining(osbbName);
        final List<Resource<Osbb>> resourceOsbbList = new ArrayList<>();
        for(Osbb o: osbbList) {
            resourceOsbbList.add(addResourceLinkToOsbb(o));
        }
        return new ResponseEntity<>(resourceOsbbList, HttpStatus.OK);
    }

    @RequestMapping(value = "/osbb/order/{field},{order}", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Osbb>>> getAllOsbbByOrder(@PathVariable("field") String field,
                                                                        @PathVariable("order") Boolean order) {
        logger.info("Get all osbb by order : " +  field);
        List<Osbb> osbbList;
        if(field.equals("name")) {
            if(order) {
                osbbList = osbbService.findAllOrderByNameAsc();
            } else {
                osbbList = osbbService.findAllOrderByNameDesc();
            }
        } else if(field.equals("district")) {
            if(order) {
                osbbList = osbbService.findAllOrderByDistrictAsc();
            } else {
                osbbList = osbbService.findAllOrderByDistrictDesc();
            }
        } else if(field.equals("creationDate")) {
            if(order) {
                osbbList = osbbService.findAllOrderByCreationDateAsc();
            } else {
                osbbList = osbbService.findAllOrderByCreationDateDesc();
            }
        } else {
            osbbList = new ArrayList<>();
        }

        final List<Resource<Osbb>> resourceOsbbList = new ArrayList<>();
        for(Osbb o: osbbList) {
            resourceOsbbList.add(addResourceLinkToOsbb(o));
        }
        return new ResponseEntity<>(resourceOsbbList, HttpStatus.OK);
    }
/*
    @RequestMapping(value = "/osbb/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Osbb>> getOsbbByName(@PathVariable("name") String name) {
        logger.info("Get one osbb by name: " + name);
        Osbb osbb = osbbService.getOsbb(name);
        return new ResponseEntity<>(addResourceLinkToOsbb(osbb), HttpStatus.OK);
    }
*/
    @RequestMapping(value = "/osbb", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Osbb>>> getAllOsbb() {
        logger.info("Get all osbb: ");
        List<Osbb> osbbList = osbbService.getAllOsbb();
        final List<Resource<Osbb>> resourceOsbbList = new ArrayList<>();

        for(Osbb o: osbbList) {
            resourceOsbbList.add(addResourceLinkToOsbb(o));
        }
        return new ResponseEntity<>(resourceOsbbList, HttpStatus.OK);
    }

    @RequestMapping(value = "/osbb", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Osbb>> updateOsbb(@RequestBody Osbb osbb) {
        logger.info("Update osbb with id: " + osbb.getOsbbId());
        Osbb updatedOsbb = osbbService.updateOsbb(osbb);
        return new ResponseEntity<>(addResourceLinkToOsbb(updatedOsbb), HttpStatus.OK);
    }

    @RequestMapping(value = "/osbb/id/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Resource<Osbb>> deleteOsbb(@PathVariable("id") Integer id) {
        logger.info("Delete osbb with id: " + id );
        osbbService.deleteOsbb(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/osbb", method = RequestMethod.DELETE)
    public ResponseEntity<Osbb>  deleteAllOsbb() {
        logger.info("Delete all osbb.");
        osbbService.deleteAllOsbb();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Resource<Osbb> addResourceLinkToOsbb(Osbb osbb) {
        if (osbb == null) return null;
        Resource<Osbb> osbbResource = new Resource<>(osbb);
        osbbResource.add(linkTo(methodOn(OsbbController.class)
                .getOsbbById(osbb.getOsbbId()))
                .withSelfRel());
        return osbbResource;
    }

}
