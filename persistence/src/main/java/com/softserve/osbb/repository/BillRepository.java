package com.softserve.osbb.repository;

import com.softserve.osbb.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by nataliia on 10.07.16.
 */
public interface BillRepository extends JpaRepository<Bill, Integer> {


    @Query(value = "select * from bill where apartment_id IN ( " +
            "SELECT apartment_id from apartment " +
            "INNER JOIN users ON users.user_id = apartment.owner_id " +
            "where users.user_id=?1" +
            ")", nativeQuery = true)
    List<Bill> findAllByUserId(Integer userId);

}
