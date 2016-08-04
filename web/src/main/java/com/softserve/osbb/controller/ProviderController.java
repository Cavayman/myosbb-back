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
 * Created by Anastasiia Fedorak on 12.07.2016.
 */
@RestController
@RequestMapping(value = "/restful/provider")
public class ProviderController {

    private static Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Autowired
    ProviderService providerService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Provider>>> listAllProviders() {
        List<Provider> providerList = providerService.findAllProviders();
        logger.info("getting all providers: " + providerList);
        final List<Resource<Provider>> resourceProviderList = new ArrayList<>();
        providerList.stream().forEach((provider) -> {
            try {
                resourceProviderList.add(addResourceLinkToProvider(provider));
            } catch (EntityNotFoundException e) {
                logger.error(e.getMessage());
            }
        });
        return new ResponseEntity<>(resourceProviderList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Provider>> getProviderById(@PathVariable("id") Integer providerId) {
        logger.info("fetching provider by id: " + providerId);
        Provider provider = providerService.findOneProviderById(providerId);
        Resource<Provider> providerResource = null;
        try {
            providerResource = addResourceLinkToProvider(provider);
        } catch (EntityNotFoundException e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(providerResource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<Provider>> createProvider(@RequestBody Provider provider) {
        Resource<Provider> providerResource;
        try {
            logger.info("saving provider object " + provider);
            providerService.saveProvider(provider);
            providerResource = addResourceLinkToProvider(provider);
        } catch (EntityNotFoundException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(providerResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Provider>> updateProvider(@PathVariable("id") Integer providerId,
                                                             @RequestBody Provider provider) {

        Resource<Provider> providerResource = null;
        try {
            logger.info("updating provider by id: " + providerId);
            provider = providerService.updateProvider(providerId, provider);
            providerResource = addResourceLinkToProvider(provider);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(providerResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Provider>>> getProvidersByName(
            @RequestParam(value = "name", required = true) String name) {
        logger.info("fetching provider by search parameter: " + name);
        List<Provider> providersBySearchTerm = providerService.findProvidersByNameOrDescription(name);
        if (providersBySearchTerm.isEmpty()) {
            logger.warn("no providers were found");
        }
        List<Resource<Provider>> resourceProviderList = new ArrayList<>();
        providersBySearchTerm.stream().forEach((provider) -> {
            try {
                resourceProviderList.add(addResourceLinkToProvider(provider));
            } catch (EntityNotFoundException e) {
                logger.error(e.getMessage());
            }
        });
        return new ResponseEntity<>(resourceProviderList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Provider> deleteProviderById(@PathVariable("id") Integer providerId){
        logger.info("removing provider by id: " + providerId);
       if (providerService.existsProvider(providerId)){
           providerService.deleteProviderById(providerId);
       }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Provider> deleteAllProviders() {
        logger.info("removing all providers");
        providerService.deleteAllProviders();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Resource<Provider> addResourceLinkToProvider(Provider provider) throws EntityNotFoundException {
        if (provider == null) throw new EntityNotFoundException();
        Resource<Provider> providerResource = new Resource<>(provider);
        providerResource.add(linkTo(methodOn(ProviderController.class)
                .getProviderById(provider.getProviderId()))
                .withSelfRel());
        return providerResource;
    }
}
