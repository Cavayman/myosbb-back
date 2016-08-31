package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.Bill;
import com.softserve.osbb.model.Provider;
import com.softserve.osbb.model.enums.Periodicity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by nataliia on 10.07.16.
 */

@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Transactional
public class BillRepositoryTest {

    @Autowired
    BillRepository billRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    private Bill bill;
    private Bill bill1;

    @Before
    public void init() {

        Provider provider = new Provider();
        provider.setPeriodicity(Periodicity.ONE_TIME);
        providerRepository.save(provider);

        Apartment apartment = new Apartment();
        apartment.setNumber(48);
        apartmentRepository.save(apartment);

        bill = new Bill();
        bill.setDate(LocalDate.now());
        bill.setTariff(6f);
        bill.setProvider(provider);
        bill.setToPay(50.6f);
        bill.setPaid(30f);
        bill.setApartment(apartment);

        bill1 = new Bill();
        bill1.setDate(LocalDate.now());
        bill1.setTariff(6f);
       bill1.setProvider(provider);
        bill1.setToPay(140.12f);
        bill1.setPaid(300f);
         bill1.setApartment(apartment);
    }

    @Test
    public void testSave() {
        billRepository.save(bill);
        assertEquals(bill, billRepository.findOne(bill.getBillId()));
    }

    @Test
    public void testSaveList() {
        List<Bill> list = new ArrayList<>();
        list.add(bill);
        list.add(bill1);
        billRepository.save(list);
        assertTrue(billRepository.findAll().containsAll(list));
    }

    @Test
    public void testFindOne() {
        billRepository.save(bill);
        assertEquals(bill, billRepository.findOne(bill.getBillId()));
    }

    @Test
    public void testFindAllByIDs() {
        List<Bill> list = new ArrayList<>();
        list.add(bill1);
        list.add(bill);
        billRepository.save(list);
        List<Integer> ids = new ArrayList<>();
        ids.add(bill.getBillId());
        ids.add(bill1.getBillId());
        assertTrue(billRepository.findAll(ids).containsAll(list));
    }

    @Test
    public void testFindAll() {
        List<Bill> list = new ArrayList<>();
        list.add(bill);
        list.add(bill1);
        billRepository.save(bill);
        billRepository.save(bill1);
        assertTrue(billRepository.findAll().containsAll(list));
    }

    @Test
    public void testDelete() {
        billRepository.save(bill);
        billRepository.delete(bill);
        assertFalse(billRepository.exists(bill.getBillId()));
    }

    @Test
    public void testDeleteById() {
        billRepository.save(bill);
        billRepository.delete(bill.getBillId());
        assertFalse(billRepository.exists(bill.getBillId()));
    }

    @Test
    public void testDeleteList() {
        List<Bill> list = new ArrayList<>();
        list.add(bill);
        list.add(bill1);
        billRepository.save(list);
        billRepository.delete(list);
        assertFalse(billRepository.exists(bill.getBillId()));
        assertFalse(billRepository.exists(bill1.getBillId()));
    }

    @Test
    public void testDeleteAll() {
        billRepository.save(bill);
        billRepository.save(bill1);
        billRepository.deleteAll();
        assertTrue(billRepository.findAll().isEmpty());
    }

    @Test
    public void testCount() {
        int before = billRepository.findAll().size();
        billRepository.save(bill);
        billRepository.save(bill1);
        assertEquals(before + 2, billRepository.count());
    }

    @Test
    public void testExists() {
        billRepository.save(bill);
        assertTrue(billRepository.exists(bill.getBillId()));
    }


}