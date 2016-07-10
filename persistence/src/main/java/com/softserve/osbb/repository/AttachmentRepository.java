package com.softserve.osbb.repository;

import com.softserve.osbb.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nataliia on 10.07.16.
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
