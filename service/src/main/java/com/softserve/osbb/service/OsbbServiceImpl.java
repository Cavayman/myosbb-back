package com.softserve.osbb.service;

import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.repository.OsbbRepository;
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
        return osbbRepository.saveAndFlush(osbb);
    }

    @Override
    public Osbb getOsbbByName(String name) {
        return osbbRepository.getByName(name);
    }

    @Override
    public Osbb getOsbbById(Integer id) {
        return osbbRepository.getOne(id);
    }

    @Override
    public List<Osbb> getAllOsbb() {
        return osbbRepository.findAll();
    }

    private Osbb compareAndAddChange(Osbb old_osbb, Osbb new_osbb) {
        if (!old_osbb.getName().equals(new_osbb.getName())) {
            old_osbb.setName(new_osbb.getName());
        }
        if (!old_osbb.getDescription().equals(new_osbb.getDescription())) {
            old_osbb.setDescription(new_osbb.getDescription());
        }
        if (!old_osbb.getContracts().equals(new_osbb.getContracts())) {
            old_osbb.setContracts(new_osbb.getContracts());
        }
        if (!old_osbb.getEvents().equals(new_osbb.getEvents())) {
            old_osbb.setEvents(new_osbb.getEvents());
        }
        if (!old_osbb.getHouses().equals(new_osbb.getHouses())) {
            old_osbb.setHouses(new_osbb.getHouses());
        }
        if (!old_osbb.getReports().equals(new_osbb.getReports())) {
            old_osbb.setReports(new_osbb.getReports());
        }
        if (!old_osbb.getStaffs().equals(new_osbb.getStaffs())) {
            old_osbb.setStaffs(new_osbb.getStaffs());
        }
        return old_osbb;
    }

    @Override
    public Osbb updateOsbb(Osbb osbb) {
        Osbb updatedOsbb = this.getOsbbById(osbb.getOsbbId());
        if(osbb.equals(updatedOsbb)) {
            return osbb;
        } else {
            return osbbRepository.saveAndFlush(compareAndAddChange(updatedOsbb, osbb));
        }
    }

    @Override
    public void removeOsbbById(Integer id) {
        osbbRepository.delete(id);
    }
}
