package com.softserve.osbb.service;

import com.softserve.osbb.model.Osbb;

import java.util.List;

/**
 * Created by Roman on 08.07.2016.
 */
public interface OsbbService {

    Osbb addOsbb(Osbb osbb);

    Osbb getOsbb(Integer id);

    Osbb getOsbb(String name);

    List<Osbb> getAllOsbb();

    long countOsbb();

    boolean existsOsbb(Integer id);

    Osbb updateOsbb(Integer osbbId, Osbb osbb);

    void deleteOsbb(Integer id);

    void deleteOsbb(Osbb osbb);

    void deleteAllOsbb();

}
