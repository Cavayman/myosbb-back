package com.softserve.osbb.service;

import com.softserve.osbb.model.Osbb;

import java.util.List;

/**
 * Created by Roman on 08.07.2016.
 */
public interface OsbbService {
    Osbb addOsbb(Osbb osbb);
    Osbb getOsbbByName(String name);
    Osbb getOsbbById(Integer id);
    List<Osbb> getAllOsbb();
    Osbb updateOsbb(Osbb osbb);
    void removeOsbbById(Integer id);
}
