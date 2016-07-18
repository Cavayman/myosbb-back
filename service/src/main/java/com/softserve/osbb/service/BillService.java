package com.softserve.osbb.service;

import com.softserve.osbb.model.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nataliia on 11.07.16.
 */

@Service
public interface BillService {

    void saveBill(Bill bill);

    void saveBillList(List<Bill> list);

    Bill findOneBillByID(Integer id);

    List<Bill> findAllBillsByIDs(List<Integer> ids);

    List<Bill> findAllBills();

    void deleteBill(Bill bill);

    void deleteBillByID(Integer id);

    void deleteListBills(List<Bill> list);

    void deleteAllBills();

    long countBills();

    boolean existsBill(Integer id);
}
