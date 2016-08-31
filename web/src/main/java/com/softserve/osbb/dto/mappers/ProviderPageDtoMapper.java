package com.softserve.osbb.dto.mappers;

import com.softserve.osbb.controller.ReportController;
import com.softserve.osbb.dto.ProviderPageDTO;
import com.softserve.osbb.model.Provider;
import com.softserve.osbb.model.ProviderType;
import com.softserve.osbb.model.enums.Periodicity;
import com.softserve.osbb.service.ProviderService;
import com.softserve.osbb.service.ProviderTypeService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Anastasiia Fedorak on 8/2/16.
 */
public class ProviderPageDtoMapper {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ProviderPageDtoMapper.class);

    public static ProviderPageDTO mapProviderEntityToDto(Integer providerId, Provider provider){
        ProviderPageDTO providerPageDTO = new ProviderPageDTO();
        if (provider != null) {
            providerPageDTO.setProviderId(provider.getProviderId());
            providerPageDTO.setName(provider.getName());
            providerPageDTO.setDescription(provider.getDescription());
            providerPageDTO.setLogoUrl(provider.getLogoUrl());
            if (provider.getPeriodicity()!=null) {
                providerPageDTO.setPeriodicity(provider.getPeriodicity().toString());
            } else providerPageDTO.setPeriodicity(Provider.DEFAULT_PERIODICITY.toString());
            providerPageDTO.setType(provider.getType());
            providerPageDTO.setEmail(provider.getEmail());
            providerPageDTO.setPhone(provider.getPhone());
            providerPageDTO.setAddress(provider.getAddress());
            providerPageDTO.setSchedule(provider.getSchedule());
            providerPageDTO.setActive(provider.isActive());
        } else {
            logger.error("provider is null");
        }
        return providerPageDTO;
    }

    public  static Provider getProviderEntityFromDto(ProviderService providerService, ProviderTypeService providerTypeService, ProviderPageDTO providerPageDTO) {
        if (providerPageDTO == null) logger.debug("empty request");
        Provider provider;
        Integer providerId = providerPageDTO.getProviderId();
        if (providerId !=null)
            provider = providerService.findOneProviderById(providerPageDTO.getProviderId());
        else provider = new Provider();
        provider.setName(providerPageDTO.getName());
        provider.setDescription(providerPageDTO.getDescription());
        provider.setLogoUrl(providerPageDTO.getLogoUrl());
        if (providerPageDTO.getPeriodicity()==null) provider.setPeriodicity(Provider.DEFAULT_PERIODICITY);
        if (providerPageDTO.getPeriodicity() != null) {
            logger.info("get periodicity "+ providerPageDTO.getPeriodicity());
            provider.setPeriodicity(Periodicity.valueOf(providerPageDTO.getPeriodicity()));
        }
        if (providerPageDTO.getType() != null) {
            try {
                if (providerTypeService.existsProviderType(providerPageDTO.getType().getProviderTypeId())) {
                    ProviderType type = providerTypeService.findOneProviderTypeById(providerPageDTO.getType().getProviderTypeId());
                    if (type != null) {
                        logger.debug("successfully find provider type entity");
                        provider.setType(type);
                    }
                }
            } catch (Exception e) {
                logger.error("cannot get dto from provider" + e.getMessage());
            }
        }
        provider.setEmail(providerPageDTO.getEmail());
        provider.setPhone(providerPageDTO.getPhone());
        provider.setAddress(providerPageDTO.getAddress());
        provider.setActive(providerPageDTO.isActive());
        provider.setSchedule(providerPageDTO.getSchedule());
        if (providerId==null) {
            logger.info("saving provider");
          providerService.saveProvider(provider);
        }
        return provider;
    }
}
