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
    public Osbb addOsbb(Osbb osbb) throws Exception{

        return osbbRepository.saveAndFlush(osbb);
    }

    @Override
    public Osbb getOsbbByName(String name) throws Exception{

        return osbbRepository.getByName(name);
    }

    @Override
    public Osbb getOsbbById(Integer id) throws Exception{

        return osbbRepository.getOne(id);
    }

    @Override
    public List<Osbb> getAllOsbb() throws Exception{

        return osbbRepository.findAll();
    }

    @Override
    public Osbb updateOsbb(Osbb osbb) throws Exception{                            // XXX

        int osbbId = osbb.getOsbbId();

        if(osbbRepository.exists(osbbId)) {

            Osbb updatedOsbb = osbbRepository.getOne(osbbId);
            updatedOsbb.setName(osbb.getName());
            updatedOsbb.setDescription(osbb.getDescription());
            updatedOsbb.setCreatorId(osbb.getCreatorId());
            updatedOsbb.setContracts(osbb.getContracts());
            updatedOsbb.setEvents(osbb.getEvents());
            updatedOsbb.setHouses(osbb.getHouses());
            updatedOsbb.setReports(osbb.getReports());
            updatedOsbb.setStaffs(osbb.getStaffs());
            return updatedOsbb;

        } else {

            throw new Exception("Osbb with id=" + osbbId
                    + " doesn't exist. First try to add this osbb.");
        }
    }

    @Override
    public void deleteOsbbById(Integer id) throws Exception{

        osbbRepository.delete(id);
    }

    @Override
    public void deleteAllOsbb() throws Exception{

        osbbRepository.deleteAll();
    }
}
