package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.repository.OsbbRepository;
import com.softserve.osbb.service.OsbbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roman on 08.07.2016.
 */
@Service
public class OsbbServiceImpl implements OsbbService {

    @Autowired
    OsbbRepository osbbRepository;

    @Override
    public Osbb addOsbb(Osbb osbb) {

        if(osbb == null) {
            return null;
        }
        return osbbRepository.saveAndFlush(osbb);
    }

    @Override
    public Osbb getOsbb(Integer id) {
        return osbbRepository.findOne(id);
    }

    @Override
    public Osbb getOsbb(String name) {
        return osbbRepository.getByName(name);
    }

    @Override
    public List<Osbb> getAllOsbb() {
        return osbbRepository.findAll();
    }

    @Override
    public long countOsbb() {
        return osbbRepository.count();
    }

    @Override
    public boolean existsOsbb(Integer id) {
        return osbbRepository.exists(id);
    }

    @Override
    public Osbb updateOsbb(Integer osbbId, Osbb osbb){
        if(osbbRepository.exists(osbbId)) {
            osbb.setOsbbId(osbbId);
            return osbbRepository.save(osbb);
        } else {
            throw new IllegalArgumentException("Osbb with id=" + osbbId
                    + " doesn't exist. First try to add this osbb.");
        }
    }

    @Override
    public void deleteOsbb(Integer id) {
        osbbRepository.delete(id);
    }

    @Override
    public void deleteOsbb(Osbb osbb) {
        osbbRepository.delete(osbb);
    }

    @Override
    public void deleteAllOsbb() {
        osbbRepository.deleteAll();
    }
}
