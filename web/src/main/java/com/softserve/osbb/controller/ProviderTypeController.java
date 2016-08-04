package com.softserve.osbb.controller;

import com.softserve.osbb.model.ProviderType;
import com.softserve.osbb.service.ProviderTypeService;
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
 * Created by Anastasiia Fedorak on 7/21/16.
 */
@RestController
@RequestMapping(value = "/restful/providertype")
@CrossOrigin
public class ProviderTypeController {

    private static Logger logger = LoggerFactory.getLogger(ProviderTypeController.class);

    @Autowired
    ProviderTypeService providerTypeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<ProviderType>>> listAllProviderTypes() {
        List<ProviderType> providerTypeList = null;
        try {
            providerTypeList = providerTypeService.findAllProviderTypes();
        } catch (Exception e) {
            logger.warn("Provider types not found");
        }
        logger.info("getting all providerTypes: " + providerTypeList);
        final List<Resource<ProviderType>> resourceProviderTypeList = new ArrayList<>();
        providerTypeList.stream().forEach((providerType) -> {
            try {
                resourceProviderTypeList.add(addResourceLinkToProviderType(providerType));
            } catch (EntityNotFoundException e) {
                logger.error(e.getMessage());
            }
        });
        return new ResponseEntity<>(resourceProviderTypeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<ProviderType>> getProviderTypeById(@PathVariable("id") Integer providerTypeId) {
        logger.info("fetching providerType by id: " + providerTypeId);
        ProviderType providerType = null;
        try {
            providerType = providerTypeService.findOneProviderTypeById(providerTypeId);
        } catch (Exception e) {
            logger.warn("Provider types not found");
        }
        Resource<ProviderType> providerTypeResource = null;
        try {
            providerTypeResource = addResourceLinkToProviderType(providerType);
        } catch (EntityNotFoundException e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(providerTypeResource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<ProviderType>> createProviderType(@RequestBody ProviderType providerType) {
        Resource<ProviderType> providerTypeResource =null;
        try {
            logger.info("saving providerType object " + providerType);
            providerTypeService.saveProviderType(providerType);
            providerTypeResource = addResourceLinkToProviderType(providerType);
        } catch (EntityNotFoundException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Cannot save provider type " + providerType.getProviderTypeName());
        }
        return new ResponseEntity<>(providerTypeResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<ProviderType>> updateProviderType(@PathVariable("id") Integer providerTypeId,
                                                             @RequestBody ProviderType providerType) {

        Resource<ProviderType> providerTypeResource = null;
        try {
            logger.info("updating providerType by id: " + providerTypeId);
            providerType = providerTypeService.updateProviderType(providerTypeId, providerType);
            providerTypeResource = addResourceLinkToProviderType(providerType);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(providerTypeResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<ProviderType>>> getProviderTypesByName(
            @RequestParam(value = "name", required = true) String name) {
        logger.info("fetching providerType by search parameter: " + name);
        List<ProviderType> providerTypesBySearchTerm = null;
        try {
            providerTypesBySearchTerm = providerTypeService.findProviderTypesByName(name);
        } catch (Exception e) {
            logger.info("Provider not found");
        }
        if (providerTypesBySearchTerm.isEmpty()) {
            logger.warn("no providerTypes were found");
        }
        List<Resource<ProviderType>> resourceProviderTypeList = new ArrayList<>();
        providerTypesBySearchTerm.stream().forEach((providerType) -> {
            try {
                resourceProviderTypeList.add(addResourceLinkToProviderType(providerType));
            } catch (EntityNotFoundException e) {
                logger.error(e.getMessage());
            }
        });
        return new ResponseEntity<>(resourceProviderTypeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ProviderType> deleteProviderTypeById(@PathVariable("id") Integer providerTypeId){
        logger.info("removing providerType by id: " + providerTypeId);
        try {
            if (providerTypeService.existsProviderType(providerTypeId)){
                providerTypeService.deleteProviderTypeById(providerTypeId);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<ProviderType> deleteAllProviderTypes() throws Exception {
        logger.info("removing all providerTypes");
        providerTypeService.deleteAllProviderTypes();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Resource<ProviderType> addResourceLinkToProviderType(ProviderType providerType) throws EntityNotFoundException {
        if (providerType == null) throw new EntityNotFoundException();
        Resource<ProviderType> providerTypeResource = new Resource<>(providerType);
        providerTypeResource.add(
                linkTo(methodOn(ProviderTypeController.class).getProviderTypeById(providerType.getProviderTypeId()
                        ))
                .withSelfRel());
        return providerTypeResource;
    }

}
