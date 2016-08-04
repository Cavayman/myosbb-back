package com.softserve.osbb.util.converters;

import com.softserve.osbb.controller.ReportController;
import com.softserve.osbb.dto.ProviderDTO;
import com.softserve.osbb.model.Provider;
import com.softserve.osbb.model.ProviderType;
import com.softserve.osbb.model.enums.Periodicity;
import com.softserve.osbb.service.ProviderService;
import com.softserve.osbb.service.ProviderTypeService;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Anastasiia Fedorak on 8/2/16.
 */
@Service
public class ProviderConverter {
    private static ProviderConverter providerConverter = new ProviderConverter();
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ReportController.class);

    private ProviderConverter(){

    }
    public  static ProviderConverter getInstance(){
        return providerConverter;
    }


    public ProviderDTO convertProviderToDto(Integer providerId, Provider provider){
        if (provider == null) {
            logger.error("provider is null");
            return new ProviderDTO(0, null, null, null,null,null);
        } else
        return  new ProviderDTO(provider.getProviderId(),
                provider.getName(), provider.getDescription(),provider.getLogoUrl(),
                provider.getPeriodicity(), provider.getType());
    }

    public Provider getProviderEntityFromDto(ProviderService providerService, ProviderTypeService providerTypeService, Integer providerId, ProviderDTO providerDTO) {
        if (providerDTO == null) logger.debug("empty request");
        logger.debug("getting entity from dto with id " + providerId);
        Provider provider = providerService.findOneProviderById(providerId);
        logger.debug("found provider");
        provider.setName(providerDTO.getName());
        logger.debug("set name" + provider.getName());
        provider.setDescription(providerDTO.getDescription());
        logger.debug("set description" + provider.getDescription());
        provider.setLogoUrl(providerDTO.getLogoUrl());
        logger.debug("set logo" + provider.getLogoUrl());
        provider.setPeriodicity(Periodicity.valueOf(providerDTO.getPeriodicity()));
        logger.debug("set periodicity " + provider.getPeriodicity());
        try {
            if (providerTypeService.existsProviderType(providerDTO.getType())){
                logger.debug("dto type: " + providerDTO.getType());
                ProviderType type = providerTypeService.findOneProviderTypeById(providerDTO.getType());
                if (type != null) {
                    logger.debug("successfully find provider type entity");
                    provider.setType(type);
                    logger.debug("type setted to " + provider.getType());
                }
            }
        } catch (Exception e) {
           logger.error("cannot get dto from provider" + e.getMessage());
        }
        return provider;
    }
}
