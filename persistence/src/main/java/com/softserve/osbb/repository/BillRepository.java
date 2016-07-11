package com.softserve.osbb.repository;

import com.softserve.osbb.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nataliia on 10.07.16.
 */
public interface BillRepository extends JpaRepository<Bill, Integer> {
}
