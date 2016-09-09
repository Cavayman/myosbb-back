package com.softserve.osbb.service;
import com.softserve.osbb.model.Apartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Oleg on 12.07.2016.
 */
@Service
public interface ApartmentService {
    void saveApartment(Apartment apartment);

    void saveApartmentList(List<Apartment> list);

    Apartment findOneApartmentByID(Integer id);

    List<Apartment> findAllApartment();

    void deleteApartment(Apartment apartment);

    void deleteApartmentByID(Integer id);

    void deleteAllApartmnets();

    long countApartments();

    boolean existsApartment(Integer id);

    Apartment updateApartment(Apartment apartment);


    Page<Apartment> getAllApartment(Integer pageNumber, String sortedBy, Boolean ascOrder,Integer number);

}
