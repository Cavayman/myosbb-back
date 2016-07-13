package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Provider;
import com.softserve.osbb.repository.ProviderRepository;
import com.softserve.osbb.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aska on 12.07.2016.
 */
@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    @Override
    public void saveProvider(Provider provider) {
        providerRepository.save(provider);
    }

    @Override
    public void saveProviderList(List<Provider> providerList) {
        providerRepository.save(providerList);
    }

    @Override
    public Provider findOneProviderById(Integer id) {
        return providerRepository.findOne(id);
    }

    @Override
    public List<Provider> findAllProvidersByIds(List<Integer> ids) {
        return providerRepository.findAll(ids);
    }

    @Override
    public List<Provider> findAllProviders() {
        return providerRepository.findAll();
    }

    @Override
    public void deleteProvider(Provider provider) {
        providerRepository.delete(provider);
    }

    @Override
    public void deleteProviderById(Integer id) {
        providerRepository.delete(id);
    }

    @Override
    public void deleteListOfProviders(List<Provider> providerList) {
        providerRepository.delete(providerList);
    }

    @Override
    public void deleteAllProviders() {
        providerRepository.deleteAll();
    }

    @Override
    public long countProviders() {
        return providerRepository.count();
    }

    @Override
    public boolean existsProvider(Integer id) {
        return providerRepository.exists(id);
    }

    @Override
    public Provider updateProvider(Integer providerId, Provider provider) throws Exception {
        boolean isExisted = providerRepository.exists(providerId);

        if (!isExisted) {
            throw new Exception("provider under id: " + provider.getProviderId() + " doesn't exist and thus" +
                    " cannot be updated");
        }
        return providerRepository.save(provider);
    }

    @Override
    public List<Provider> findProvidersByNameOrDescription(String name) {
        return providerRepository.findProvidersByNameOrDescription(name);
    }


}
