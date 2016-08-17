package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Provider;
import com.softserve.osbb.repository.ProviderRepository;
import com.softserve.osbb.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Aska on 12.07.2016.
 */
@Service
public class ProviderServiceImpl implements ProviderService {
    private static final int DEF_ROWS = 10;

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
    public Provider updateProvider(Integer providerId, Provider provider) throws EntityNotFoundException {
        boolean isExisted = providerRepository.exists(providerId);

        if (!isExisted) {
            throw new EntityNotFoundException("provider under id: " + provider.getProviderId() + " doesn't exist and " +
                    " cannot be updated");
        }
        return providerRepository.save(provider);
    }

    @Override
    public List<Provider> findProvidersByNameOrDescription(String name) {
        return providerRepository.findProvidersByNameOrDescription(name);
    }

    @Override
    public Page<Provider> getProviders(Integer pageNumber, String sortBy, Boolean order) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, DEF_ROWS,
                getSortingOrder(order), sortBy == null ? "name" : sortBy);
        return providerRepository.findAll(pageRequest);
    }

    @Override
    public Page<Provider> findByActiveTrue(Integer pageNumber, String sortBy, Boolean order) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, DEF_ROWS,
                getSortingOrder(order), sortBy == null ? "name" : sortBy);
        return providerRepository.findByActiveTrue(pageRequest);
    }

    @Override
    public List<Provider> findByActiveTrue() {
        return providerRepository.findByActiveTrue();
    }

    public Sort.Direction getSortingOrder(Boolean order) {
        if (order == null) {
            return Sort.Direction.DESC;
        }
        return order == true ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    @Override
    public List<Provider> findProvidersByNameOrDescription(String name, Integer pageNumber) {
        return null; //TODO: implement pagination
    }
}
