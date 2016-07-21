package com.softserve.osbb.controller;

import com.softserve.osbb.model.Provider;
import com.softserve.osbb.service.ProviderService;
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
@RequestMapping(value = "/restful/providertype")
public class ProviderTypeController {

    private static Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Autowired
    ProviderService providerTypeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Provider>>> listAllProviders() {
        List<Provider> providerTypeList = providerTypeService.findAllProviders();
        logger.info("getting all providerTypes: " + providerTypeList);
        final List<Resource<Provider>> resourceProviderList = new ArrayList<>();
        providerTypeList.stream().forEach((providerType) -> {
            try {
                resourceProviderList.add(addResourceLinkToProvider(providerType));
            } catch (EntityNotFoundException e) {
                logger.error(e.getMessage());
            }
        });
        return new ResponseEntity<>(resourceProviderList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Provider>> getProviderById(@PathVariable("id") Integer providerTypeId) {
        logger.info("fetching providerType by id: " + providerTypeId);
        Provider providerType = providerTypeService.findOneProviderById(providerTypeId);
        Resource<Provider> providerTypeResource = null;
        try {
            providerTypeResource = addResourceLinkToProvider(providerType);
        } catch (EntityNotFoundException e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(providerTypeResource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<Provider>> createProvider(@RequestBody Provider providerType) {
        Resource<Provider> providerTypeResource;
        try {
            logger.info("saving providerType object " + providerType);
            providerTypeService.saveProvider(providerType);
            providerTypeResource = addResourceLinkToProvider(providerType);
        } catch (EntityNotFoundException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(providerTypeResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Provider>> updateProvider(@PathVariable("id") Integer providerTypeId,
                                                             @RequestBody Provider providerType) {

        Resource<Provider> providerTypeResource = null;
        try {
            logger.info("updating providerType by id: " + providerTypeId);
            providerType = providerTypeService.updateProvider(providerTypeId, providerType);
            providerTypeResource = addResourceLinkToProvider(providerType);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(providerTypeResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Provider>>> getProvidersByName(
            @RequestParam(value = "name", required = true) String name) {
        logger.info("fetching providerType by search parameter: " + name);
        List<Provider> providerTypesBySearchTerm = providerTypeService.findProvidersByNameOrDescription(name);
        if (providerTypesBySearchTerm.isEmpty()) {
            logger.warn("no providerTypes were found");
        }
        List<Resource<Provider>> resourceProviderList = new ArrayList<>();
        providerTypesBySearchTerm.stream().forEach((providerType) -> {
            try {
                resourceProviderList.add(addResourceLinkToProvider(providerType));
            } catch (EntityNotFoundException e) {
                logger.error(e.getMessage());
            }
        });
        return new ResponseEntity<>(resourceProviderList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Provider> deleteProviderById(@PathVariable("id") Integer providerTypeId){
        logger.info("removing providerType by id: " + providerTypeId);
        if (providerTypeService.existsProvider(providerTypeId)){
            providerTypeService.deleteProviderById(providerTypeId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<Provider> deleteAllProviders() {
        logger.info("removing all providerTypes");
        providerTypeService.deleteAllProviders();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Resource<Provider> addResourceLinkToProvider(Provider providerType) throws EntityNotFoundException {
        if (providerType == null) throw new EntityNotFoundException();
        Resource<Provider> providerTypeResource = new Resource<>(providerType);
        providerTypeResource.add(linkTo(methodOn(ProviderController.class)
                .getProviderById(providerType.getProviderId()))
                .withSelfRel());
        return providerTypeResource;
    }

}
