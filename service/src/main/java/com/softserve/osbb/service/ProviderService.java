package com.softserve.osbb.service;

import com.softserve.osbb.model.Provider;

import java.util.List;

/**
 * Created by Aska on 12.07.2016.
 */
public interface ProviderService {

    void saveProvider(Provider provider);

    void saveProviderList(List<Provider> providerList);

    Provider findOneProviderById(Integer id);

    List<Provider> findAllProvidersByIds(List<Integer> ids);

    List<Provider> findAllProviders();

    void deleteProvider(Provider provider);

    void deleteProviderById(Integer id);

    void deleteListOfProviders(List<Provider> providerList);

    void deleteAllProviders();

    long countProviders();

    boolean existsProvider(Integer id);

}
