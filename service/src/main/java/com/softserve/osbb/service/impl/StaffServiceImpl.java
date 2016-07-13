package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Staff;
import com.softserve.osbb.repository.StaffRepository;
import com.softserve.osbb.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Aska on 12.07.2016.
 */
@Service
public class StaffServiceImpl implements StaffService{

    @Autowired
    StaffRepository staffRepository;

    @Override
    public void saveStaff(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void saveStaffList(List<Staff> staffList) {
        staffRepository.save(staffList);
    }

    @Override
    public Staff findOneStaffById(Integer id) {
        return staffRepository.findOne(id);
    }

    @Override
    public Staff updateStaff(Integer id, Staff staff) throws EntityNotFoundException {
        if (!staffRepository.exists(id)) throw new EntityNotFoundException("Staff with id "+
        id + " doesn't exist and cannot be updated");
        return staffRepository.save(staff);
    }

    @Override
    public List<Staff> findAllStaffsByIds(List<Integer> ids) {
        return staffRepository.findAll(ids);
    }

    @Override
    public List<Staff> findAllStaffs() {
        return staffRepository.findAll();
    }

    @Override
    public void deleteStaff(Staff staff) {
        staffRepository.delete(staff);
    }

    @Override
    public void deleteStaffById(Integer id) {
        staffRepository.delete(id);
    }

    @Override
    public void deleteListOfStaffs(List<Staff> staffList) {
        staffRepository.delete(staffList);
    }

    @Override
    public void deleteAllStaffs() {
        staffRepository.deleteAll();
    }

    @Override
    public long countStaffs() {
        return staffRepository.count();
    }

    @Override
    public boolean existsStaff(Integer id) {
        return staffRepository.exists(id);
    }
}
