package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.Bill;
import com.softserve.osbb.model.User;
import com.softserve.osbb.repository.ApartmentRepository;
import com.softserve.osbb.repository.BillRepository;
import com.softserve.osbb.repository.UserRepository;
import com.softserve.osbb.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nataliia on 11.07.16.
 */

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Override
    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void saveBillList(List<Bill> list) {
        billRepository.save(list);
    }

    @Override
    public Bill findOneBillByID(Integer id) {
        return billRepository.findOne(id);
    }

    @Override
    public List<Bill> findAllBillsByIDs(List<Integer> ids) {
        return billRepository.findAll(ids);
    }

    @Override
    public Page<Bill> findAllBills(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    @Override
    public List<Bill> findAllByUserId(Integer userId) {
        return billRepository.findAllByUserId(userId);
    }

    @Override
    public Page<Bill> findAllByApartmentOwner(Integer ownerId, Pageable pageable) {
        User apartmentOwner = userRepository.findOne(ownerId);
        Page<Bill> bills = null;
        if (apartmentOwner != null) {
           Apartment ownersApartment = apartmentRepository.findByOwner(apartmentOwner.getUserId());
           bills = billRepository.findByApartment(ownersApartment, pageable);
        }
        return bills;
    }

    @Override
    public void deleteBill(Bill bill) {
        billRepository.delete(bill);
    }

    @Override
    public boolean deleteBillByID(Integer id) {
        boolean billExists = billRepository.exists(id);
        if (!billExists) {
            return false;
        }
        billRepository.delete(id);
        return true;
    }

    @Override
    public void deleteListBills(List<Bill> list) {
        billRepository.delete(list);
    }

    @Override
    public void deleteAllBills() {
        billRepository.deleteAll();
    }

    @Override
    public long countBills() {
        return billRepository.count();
    }

    @Override
    public boolean existsBill(Integer id) {
        return billRepository.exists(id);
    }
}
