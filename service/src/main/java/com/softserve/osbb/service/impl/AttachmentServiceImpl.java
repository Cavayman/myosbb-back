package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Attachment;
import com.softserve.osbb.repository.AttachmentRepository;
import com.softserve.osbb.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nataliia on 11.07.16.
 */

@Service
public class AttachmentServiceImpl implements AttachmentService{

    @Autowired
    AttachmentRepository attachmentRepository;

    private static final int DEF_ROWS = 10;

    @Override
    public Attachment saveAttachment(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    @Override
    public List<Attachment> saveAttachments(List<Attachment> list) {
        return attachmentRepository.save(list);
    }

    @Override
    public List<Attachment> getAttachments(List<Attachment> list) {
        return attachmentRepository.save(list);
    }

    @Override
    public Attachment getAttachmentById(Integer id) {
        return attachmentRepository.findOne(id);
    }

    @Override
    public List<Attachment> getAttachmentsByIds(List<Integer> ids) {
        return attachmentRepository.findAll(ids);
    }

    @Override
    public List<Attachment> getAllAttachments() {
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
    public void deleteAttachments(List<Attachment> list) {
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

    @Override
    public Page<Attachment> getAllAttachments(Integer pageNumber, String sortBy, Boolean order) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, DEF_ROWS,
                getSortingOrder(order), sortBy == null ? "date" : sortBy);
        return attachmentRepository.findAll(pageRequest);
    }

    public Sort.Direction getSortingOrder(Boolean order) {
        if (order == null) {
            return Sort.Direction.DESC;
        }
        return order == true ? Sort.Direction.ASC : Sort.Direction.DESC;
    }
}
