package com.softserve.osbb.service;

import com.softserve.osbb.model.Staff;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Aska on 12.07.2016.
 */

@Service
public interface StaffService {
    void saveStaff(Staff Staff);

    void saveStaffList(List<Staff> StaffList);

    Staff findOneStaffById(Integer id);

    List<Staff> findAllStaffsByIds(List<Integer> ids);

    List<Staff> findAllStaffs();

    void deleteStaff(Staff Staff);

    void deleteStaffById(Integer id);

    void deleteListOfStaffs(List<Staff> StaffList);

    void deleteAllStaffs();

    long countStaffs();

    boolean existsStaff(Integer id);

    Staff updateStaff(Integer id, Staff staff) throws EntityNotFoundException;
}
