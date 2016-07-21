package com.softserve.osbb.service;

import com.softserve.osbb.model.ProviderType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Anastasiia Fedorak on 7/21/16.
 */

@Service
public interface ProviderTypeService  {

    void saveProviderType(ProviderType providerType) throws Exception;

    ProviderType findOneProviderTypeById(Integer id) throws Exception;

    List<ProviderType> findAllProviderTypes() throws Exception;

    void deleteProviderType(ProviderType provider) throws Exception;

    void deleteProviderTypeById(Integer id) throws Exception;

    void deleteAllProviderTypes() throws Exception;

    long countProviderTypes() throws Exception;

    boolean existsProviderType(Integer id) throws Exception;

    ProviderType updateProviderType(Integer providerId, ProviderType provider) throws Exception;

    List<ProviderType> findProviderTypesByName(String typeName) throws Exception;

}
