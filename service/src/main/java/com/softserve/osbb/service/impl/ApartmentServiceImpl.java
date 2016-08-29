package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.repository.ApartmentRepository;
import com.softserve.osbb.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    ApartmentRepository apartmentRepository;


    private static final int DEF_ROWS = 10;

    @Override
    public void saveApartment(Apartment apartment) {
        apartmentRepository.save(apartment);
    }

    @Override
    public void saveApartmentList(List<Apartment> list) {

        apartmentRepository.save(list);
    }

    @Override
    public Apartment findOneApartmentByID(Integer id) {

        return apartmentRepository.findOne(id);
    }


    @Override
    public List<Apartment> findAllApartment() {

        return apartmentRepository.findAll();
    }

    @Override
    public void deleteApartment(Apartment apartment) {

        apartmentRepository.delete(apartment);
    }

    @Override
    public void deleteApartmentByID(Integer id) {

        apartmentRepository.delete(id);
    }


    @Override
    public void deleteAllApartmnets() {

        apartmentRepository.deleteAll();
    }

    @Override
    public Apartment updateApartment(Apartment apartment) {
        return

                apartmentRepository.saveAndFlush(apartment);
    }

    @Override
    public long countApartments() {

        return apartmentRepository.count();
    }

    @Override
    public boolean existsApartment(Integer id) {

        return apartmentRepository.exists(id);
    }


    @Override
    public Page<Apartment> getAllApartment(Integer pageNumber, String sortBy, Boolean order) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, DEF_ROWS,
                getSortingOrder(order), sortBy == null ? "number" : sortBy);
        return apartmentRepository.findAll(pageRequest);
    }


    public Sort.Direction getSortingOrder(Boolean order) {
        if (order == null) {
            return Sort.Direction.DESC;
        }
        return order == true ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

}