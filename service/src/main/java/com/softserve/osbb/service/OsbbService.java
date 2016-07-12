package com.softserve.osbb.service;

import com.softserve.osbb.model.Osbb;

import java.util.List;

/**
 * Created by Roman on 08.07.2016.
 */
public interface OsbbService {

    Osbb addOsbb(Osbb osbb);

    void deleteOsbbById(Integer id);

    Osbb getOsbbByName(String name);

    List<Osbb> getAllOsbb();

    long countOsbb();

    boolean existsOsbb(Integer id);

    Osbb getOsbbById(Integer id);

    Osbb updateOsbb(Osbb osbb);

    void deleteAllOsbb();

}
