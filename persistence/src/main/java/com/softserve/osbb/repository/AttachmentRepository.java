package com.softserve.osbb.repository;

import com.softserve.osbb.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nataliia on 10.07.16.
 */
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    Attachment findByPath(String path);
}
