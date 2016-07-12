package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Bill;
import com.softserve.osbb.repository.BillRepository;
import com.softserve.osbb.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nataliia on 11.07.16.
 */

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    BillRepository billRepository;

    @Override
    public void saveBill(Bill bill) {
        billRepository.save(bill);
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
    public List<Bill> findAllBills() {
        return billRepository.findAll();
    }

    @Override
    public void deleteBill(Bill bill) {
        billRepository.delete(bill);
    }

    @Override
    public void deleteBillByID(Integer id) {
        billRepository.delete(id);
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
