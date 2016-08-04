package com.softserve.osbb.controller;

import com.softserve.osbb.dto.ProviderDTO;
import com.softserve.osbb.model.Provider;
import com.softserve.osbb.service.ProviderService;
import com.softserve.osbb.service.ProviderTypeService;
import com.softserve.osbb.util.EntityNotFoundException;
import com.softserve.osbb.util.PageCreator;
import com.softserve.osbb.util.converters.ProviderConverter;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Anastasiia Fedorak on 12.07.2016.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/restful/provider")
public class ProviderController {

    private static Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Autowired
    ProviderService providerService;

    @Autowired
    ProviderTypeService providerTypeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<ProviderDTO>>> listAllProviders() {
        List<Provider> providerList = providerService.findAllProviders();
        logger.info("getting all providers: " + providerList);

        List<ProviderDTO> providerDtoList = new ArrayList<>();

        final List<Resource<ProviderDTO>> resourceProviderList = new ArrayList<>();
        providerList.stream().forEach((provider) -> {
            ProviderDTO providerDTO =  ProviderConverter.getInstance().convertProviderToDto(provider.getProviderId(), provider);
            try {
                resourceProviderList.add(addResourceLinkToProvider(providerDTO));
            } catch (EntityNotFoundException e) {
                logger.error(e.getMessage());
            }
        });
        return new ResponseEntity<>(resourceProviderList, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<PageCreator<Resource<ProviderDTO>>> listAllProviders(
            @RequestParam(value = "pageNum", required = true) Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "asc", required = false) Boolean ascOrder) {
        logger.info("getting all providers by page number: " + pageNumber);
        Page<Provider> providersByPage = providerService.getProviders(pageNumber, sortedBy, ascOrder);

        int currentPage = providersByPage.getNumber() + 1;
        logger.info("current page : " + currentPage);
        int begin = Math.max(1, currentPage - 5);
        logger.info("starts with: " + begin);
        int totalPages = providersByPage.getTotalPages();
        int end = Math.min(currentPage + 5, totalPages);
        logger.info("ends with: " + totalPages);

        List<Resource<ProviderDTO>> resourceList = new ArrayList<>();
        providersByPage.forEach((provider) -> {
            ProviderDTO providerDTO = ProviderConverter.getInstance().convertProviderToDto(provider.getProviderId(), provider);
            try {
                resourceList.add(addResourceLinkToProvider(providerDTO));
            } catch (EntityNotFoundException e) {
                logger.error(e.getMessage());
            }
        });

        PageCreator<Resource<ProviderDTO>> pageCreator = new PageCreator<>();
        pageCreator.setRows(resourceList);
        pageCreator.setCurrentPage(Integer.valueOf(currentPage).toString());
        pageCreator.setBeginPage(Integer.valueOf(begin).toString());
        pageCreator.setEndPage(Integer.valueOf(end).toString());
        pageCreator.setTotalPages(Integer.valueOf(totalPages).toString());

        return new ResponseEntity<>(pageCreator, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<ProviderDTO>> getProviderById(@PathVariable("id") Integer providerId) {
        logger.info("fetching provider by id: " + providerId);
        Provider provider = providerService.findOneProviderById(providerId);
        Resource<ProviderDTO> providerResource = null;
        try {
            providerResource = addResourceLinkToProvider(
                    ProviderConverter.getInstance().convertProviderToDto(providerId, provider));
        } catch (EntityNotFoundException e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(providerResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Resource<ProviderDTO>> createProvider(@RequestBody Provider provider) {
        Resource<ProviderDTO> providerResource;
        try {
            logger.info("saving provider object " + provider);
            providerService.saveProvider(provider);
            ProviderDTO providerDto =  ProviderConverter.getInstance().convertProviderToDto(provider.getProviderId(), provider);
            logger.info("provider dto" + providerDto.getName());
            providerResource = addResourceLinkToProvider(providerDto);
            logger.debug("added link");
        } catch (EntityNotFoundException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(providerResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<ProviderDTO>> updateProvider(@PathVariable("id") Integer providerId,
                                                             @RequestBody ProviderDTO providerDTO) {

        Resource<ProviderDTO> providerResource = null;
        Provider provider;
        try {
            logger.info("updating provider by id: " + providerId);
            if (providerService.existsProvider(providerId)){
                provider = providerService.updateProvider(providerId,
                        ProviderConverter.getInstance().
                        getProviderEntityFromDto(providerService, providerTypeService, providerId, providerDTO));
                ProviderDTO providerDto = ProviderConverter.getInstance().
                        convertProviderToDto(providerId, provider);
                providerResource = addResourceLinkToProvider(providerDto);
            } else logger.error("provider not found");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(providerResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<ProviderDTO>>> getProvidersByName(
            @RequestParam(value = "name", required = true) String name) {
        logger.info("fetching provider by search parameter: " + name);
        List<Provider> providersBySearchTerm = providerService.findProvidersByNameOrDescription(name);
        if (providersBySearchTerm.isEmpty()) {
            logger.warn("no providers were found");
        }
        List<Resource<ProviderDTO>> resourceProviderList = new ArrayList<>();
        providersBySearchTerm.stream().forEach((provider) -> {
            try {
                resourceProviderList.add(addResourceLinkToProvider(
                        ProviderConverter.getInstance().convertProviderToDto(provider.getProviderId(), provider)));
            } catch (EntityNotFoundException e) {
                logger.error(e.getMessage());
            }
        });
        return new ResponseEntity<>(resourceProviderList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ProviderDTO> deleteProviderById(@PathVariable("id") Integer providerId){
        logger.info("removing provider by id: " + providerId);
       if (providerService.existsProvider(providerId)){
           providerService.deleteProviderById(providerId);
       }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<ProviderDTO> deleteAllProviders() {
        logger.info("removing all providers");
        providerService.deleteAllProviders();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Resource<ProviderDTO> addResourceLinkToProvider(ProviderDTO provider) throws EntityNotFoundException {
        if (provider == null) throw new EntityNotFoundException();
        Resource<ProviderDTO> providerResource = new Resource<>(provider);
        providerResource.add(linkTo(methodOn(ProviderController.class)
                .getProviderById(provider.getProviderId()))
                .withSelfRel());
        return providerResource;
    }
}
