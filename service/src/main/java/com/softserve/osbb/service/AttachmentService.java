package com.softserve.osbb.service;

import com.softserve.osbb.model.Attachment;
import java.util.List;

/**
 * Created by nataliia on 11.07.16.
 */
public interface AttachmentService {

    void saveAttachment(Attachment attachment);

    void saveAttachmentList(List<Attachment> list);

    Attachment findOneAttachmentByID(Integer id);

    List<Attachment> findAllAttachmentsByIDs(List<Integer> ids);

    List<Attachment> findAllAttachments();

    void deleteAttachment(Attachment attachment);

    void deleteAttachmentByID(Integer id);

    void deleteListAttachments(List<Attachment> list);

    void deleteAllAttachments();

    long countAttachments();

    boolean exitsAttachment(Integer id);
}
