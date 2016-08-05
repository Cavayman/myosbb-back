package com.softserve.osbb.service;

import com.softserve.osbb.model.Attachment;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by nataliia on 11.07.16.
 */
public interface AttachmentService {

    void uploadFile(MultipartFile file) throws IOException;

    Attachment downloadFile(String filename);

    Attachment saveAttachment(Attachment attachment);

    List<Attachment> saveAttachments(List<Attachment> list);

    Attachment getAttachmentById(Integer id);

    List<Attachment> getAttachments(List<Attachment> list);

    List<Attachment> getAttachmentsByIds(List<Integer> ids);

    List<Attachment> getAllAttachments();

    Attachment updateAttachment(Integer id, Attachment attachment);

    void deleteAttachment(Attachment attachment);

    void deleteAttachmentById(Integer id);

    void deleteAttachments(List<Attachment> list);

    void deleteAllAttachments();

    long countAttachments();

    boolean existsAttachment(Integer id);

    Page<Attachment> getAllAttachments(Integer pageNumber, String sortedBy, Boolean ascOrder);
}
