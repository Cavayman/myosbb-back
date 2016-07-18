package com.softserve.osbb.service;

import com.softserve.osbb.model.Attachment;

import java.util.List;

/**
 * Created by nataliia on 11.07.16.
 */
public interface AttachmentService {

    Attachment saveAttachment(Attachment attachment);

    List<Attachment> saveAttachments(List<Attachment> list);

    Attachment findAttachmentById(Integer id);

    List<Attachment> findAttachments(List<Attachment> list);

    List<Attachment> findAttachmentsByIds(List<Integer> ids);

    List<Attachment> findAllAttachments();

    Attachment updateAttachment(Integer id, Attachment attachment);

    void deleteAttachment(Attachment attachment);

    void deleteAttachmentById(Integer id);

    void deleteAttachments(List<Attachment> list);

    void deleteAllAttachments();

    long countAttachments();

    boolean existsAttachment(Integer id);
}
