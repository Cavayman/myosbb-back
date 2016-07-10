package com.softserve.osbb.service;

import com.softserve.osbb.model.Osbb;

import java.util.List;

/**
 * Created by Roman on 08.07.2016.
 */
public interface OsbbService {

    Osbb addOsbb(Osbb osbb) throws Exception;

    Osbb getOsbbByName(String name)  throws Exception;

    Osbb getOsbbById(Integer id)  throws Exception;

    List<Osbb> getAllOsbb()  throws Exception;

    Osbb updateOsbb(Osbb osbb)  throws Exception;

    void deleteOsbbById(Integer id)  throws Exception;

    void deleteAll()  throws Exception;

}
