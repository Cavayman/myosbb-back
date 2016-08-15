package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Attachment;
import com.softserve.osbb.repository.AttachmentRepository;
import com.softserve.osbb.service.AttachmentService;
import liquibase.util.file.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by nataliia on 11.07.16.
 */

@Service
public class AttachmentServiceImpl implements AttachmentService{

    @Autowired
    AttachmentRepository attachmentRepository;

    public static final String ROOT = "upload-dir";

    private static final int DEF_ROWS = 10;

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        Path attachmentPath = saveFile(getFilePathWithSubDir(file), file);
        Attachment attachment = new Attachment();
        attachment.setPath(attachmentPath.toString().replaceFirst(ROOT, ""));
        saveAttachment(attachment);
    }

    private Path getFilePathWithSubDir(MultipartFile file) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Path path = Paths.get(ROOT + "/" + dateFormat.format(new Date()));
        Files.createDirectories(path);
        return Paths.get(String.valueOf(path) + "/" + file.getOriginalFilename());
    }

    private Path saveFile(Path newFilePath, MultipartFile file) throws IOException {
        if (!Files.exists(newFilePath)) {
            Files.copy(file.getInputStream(), newFilePath);
            return newFilePath;
        } else {
            int i = 0;
            Path tempPath = newFilePath;
            while (true) {
                try {
                    Files.copy(file.getInputStream(), tempPath);
                    return tempPath;
                } catch (FileAlreadyExistsException ex) {
                    String filePathWithoutExtension = newFilePath.toString().substring(0, newFilePath.toString().lastIndexOf("."));
                    String fileExtension = newFilePath.toString().substring(newFilePath.toString().lastIndexOf("."));
                    tempPath = Paths.get(filePathWithoutExtension + "(" + ++i + ")" + fileExtension);
                }
            }
        }
    }

    @Override
    public void deleteAttachmentEverywhere(Integer attachmentId) throws IOException {
        File delFile = new File(ROOT + attachmentRepository.findOne(attachmentId).getPath());
        String fileNameWithOutExt = FilenameUtils.removeExtension(delFile.getPath());
        String extension = FilenameUtils.getExtension(delFile.getPath());
        File newDelFile = new File(fileNameWithOutExt + "_del." + extension);
        Files.move(delFile.toPath(), newDelFile.toPath());
        attachmentRepository.delete(attachmentRepository.findOne(attachmentId));
    }

    @Override
    public Attachment downloadFile(String filename) {
        return attachmentRepository.findByPath(filename);
    }

    @Override
    public List<Attachment> findAttachmentByPath(String path) {
        return attachmentRepository.findAttachmentByPath(path);
    }

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
                getSortingOrder(order), sortBy == null ? "path" : sortBy);
        return attachmentRepository.findAll(pageRequest);
    }

    public Sort.Direction getSortingOrder(Boolean order) {
        if (order == null) {
            return Sort.Direction.DESC;
        }
        return order == true ? Sort.Direction.ASC : Sort.Direction.DESC;
    }
}
