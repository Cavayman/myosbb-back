package com.softserve.osbb.service;
import com.softserve.osbb.model.Apartment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oleg on 12.07.2016.
 */
@Service
public interface ApartmentService {
    void saveApartment(Apartment apartment);

    void saveApartmentList(List<Apartment> list);

    Apartment findOneApartmentByID(Integer id);

    List<Apartment> findAllApartmentByIDs(List<Integer> ids);

    List<Apartment> findAllApartment();

    void deleteApartment(Apartment apartment);

    void deleteApartmentByID(Integer id);

    void deleteListApartments(List<Apartment> list);

    void deleteAllApartmnets();

    long countApartments();

    boolean existsApartment(Integer id);

    void updateApartment(Apartment apartment);

    Apartment findOneApartmentByNumber(Integer number);
}
