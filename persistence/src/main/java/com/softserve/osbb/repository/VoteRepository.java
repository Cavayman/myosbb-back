package com.softserve.osbb.repository;

import com.softserve.osbb.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Roman on 06.07.2016.
 */

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
