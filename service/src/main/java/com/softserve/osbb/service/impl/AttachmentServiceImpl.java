package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Attachment;
import com.softserve.osbb.repository.AttachmentRepository;
import com.softserve.osbb.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nataliia on 11.07.16.
 */

@Service
public class AttachmentServiceImpl implements AttachmentService{

    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public Attachment saveAttachment(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    @Override
    public List<Attachment> findAttachments(List<Attachment> list) {
        return attachmentRepository.save(list);
    }

    @Override
    public Attachment findAttachmentById(Integer id) {
        return attachmentRepository.findOne(id);
    }

    @Override
    public List<Attachment> findAttachmentsByIds(List<Integer> ids) {
        return attachmentRepository.findAll(ids);
    }

    @Override
    public List<Attachment> findAllAttachments() {
        return attachmentRepository.findAll();
    }

    @Override
    public Attachment updateAttachment(Integer id, Attachment attachment) {
        return attachmentRepository.exists(id) ? attachmentRepository.save(attachment) : null;
    }

    @Override
    public void deleteAttachment(Attachment attachment) {
        attachmentRepository.delete(attachment);
    }

    @Override
    public void deleteAttachmentById(Integer id) {
        attachmentRepository.delete(id);
    }

    @Override
    public void deleteListAttachments(List<Attachment> list) {
        attachmentRepository.delete(list);
    }

    @Override
    public void deleteAllAttachments() {
        attachmentRepository.deleteAll();
    }

    @Override
    public long countAttachments() {
        return attachmentRepository.count();
    }

    @Override
    public boolean existsAttachment(Integer id) {
        return attachmentRepository.exists(id);
    }
}
