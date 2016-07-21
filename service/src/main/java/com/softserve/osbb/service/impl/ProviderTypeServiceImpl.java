package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.ProviderType;
import com.softserve.osbb.repository.ProviderTypeRepository;
import com.softserve.osbb.service.ProviderTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Anastasiia Fedorak on 7/21/16.
 */
public class ProviderTypeServiceImpl implements ProviderTypeService{

    @Autowired
    ProviderTypeRepository providerTypeRepository;


    @Override
    public void saveProviderType(ProviderType providerType) throws Exception {
        providerTypeRepository.save(providerType);
    }

    @Override
    public ProviderType findOneProviderTypeById(Integer id) throws Exception {
        return providerTypeRepository.findOne(id);
    }

    @Override
    public List<ProviderType> findAllProviderTypes() throws Exception {
        return providerTypeRepository.findAll();
    }

    @Override
    public void deleteProviderType(ProviderType provider) throws Exception {
        providerTypeRepository.delete(provider);
    }

    @Override
    public void deleteProviderTypeById(Integer id) throws Exception {
        providerTypeRepository.delete(id);
    }

    @Override
    public void deleteAllProviderTypes() throws Exception {
        providerTypeRepository.deleteAll();
    }

    @Override
    public long countProviderTypes() throws Exception {
        return providerTypeRepository.count();
    }

    @Override
    public boolean existsProviderType(Integer id) throws Exception {
        return providerTypeRepository.exists(id);
    }

    @Override
    public ProviderType updateProviderType(Integer providerTypeId, ProviderType providerType) throws Exception {
        boolean isExisted = providerTypeRepository.exists(providerTypeId);

        if (!isExisted) {
            throw new EntityNotFoundException("Provider type under id " + providerTypeId + " doesn't exist and " +
                    " cannot be updated");
        }
        return providerTypeRepository.save(providerType);
    }

    @Override
    public List<ProviderType> findProviderTypesByName(String typeName) throws Exception {
        return providerTypeRepository.findProviderTypesByName(typeName);
    }
}
