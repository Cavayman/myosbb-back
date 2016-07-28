package com.softserve.osbb.repository;

import com.softserve.osbb.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Roman on 28.07.2016.
 */
@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {
}
