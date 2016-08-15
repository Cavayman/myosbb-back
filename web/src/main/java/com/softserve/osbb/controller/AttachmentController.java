package com.softserve.osbb.controller;

import com.softserve.osbb.model.Attachment;
import com.softserve.osbb.service.AttachmentService;
import com.softserve.osbb.util.PageCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.softserve.osbb.util.ResourceUtil.toResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by nataliia on 11.07.16.
 */

@CrossOrigin
@RestController
@RequestMapping("/restful/attachment")
public class AttachmentController {

    private static Logger logger = LoggerFactory.getLogger(AttachmentController.class);

    @Autowired
    private AttachmentService attachmentService;

    public static final String ROOT = "upload-dir";

    private final ResourceLoader resourceLoader;

    @Autowired
    public AttachmentController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(value = "//", method = RequestMethod.GET)
    public void provideUploadInfo(Model model) throws IOException {
        model.addAttribute("files", Files.walk(Paths.get(ROOT))
                .filter(path -> !path.equals(Paths.get(ROOT)))
                .map(path -> Paths.get(ROOT).relativize(path))
//                .map(path -> linkTo(methodOn(AttachmentController.class).getFile(path.toString())).withRel(path.toString()))
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "/{subdir}/{filename:.+}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String subdir, @PathVariable String filename) {
        try {
            logger.info("Getting file " + filename);
            return new ResponseEntity<>(resourceLoader.getResource(
                    "file:" + ROOT + "/" + attachmentService.downloadFile("/" + subdir + "/" + filename).getPath()), HttpStatus.OK);
        } catch (Exception e) {
            logger.warn("File "+ filename + " not found.");
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                logger.info("Uploading file " + file.getOriginalFilename());
                attachmentService.uploadFile(file);
                return new ResponseEntity<>(attachmentService.downloadFile(file.getOriginalFilename()), HttpStatus.OK);
            } catch (IOException | RuntimeException e) {
                logger.warn("Cannot upload file " + file.getOriginalFilename());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            logger.warn("Cannot upload file " + file.getOriginalFilename() + " because it is empty.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Attachment>>> findAllAttachments() {
        List<Attachment> attachmentList = attachmentService.getAllAttachments();
        logger.info("Getting all attachments.");
        final List<Resource<Attachment>> resourceAttachmentList = new ArrayList<>();
        for (Attachment e : attachmentList) {
            Resource<Attachment> attachmentResource = new Resource<>(e);
            attachmentResource.add(linkTo(methodOn(AttachmentController.class)
                    .findAttachmentById(e.getAttachmentId()))
                    .withSelfRel());
            resourceAttachmentList.add(attachmentResource);
        }
        return new ResponseEntity<>(resourceAttachmentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Attachment>> findAttachmentById(@PathVariable("id") Integer attachmentId) {
        logger.info("Getting attachment by id: " + attachmentId);
        Attachment attachment = attachmentService.getAttachmentById(attachmentId);
        Resource<Attachment> attachmentResource = new Resource<>(attachment);
        attachmentResource.add(linkTo(methodOn(AttachmentController.class).findAttachmentById(attachmentId)).withSelfRel());
        return new ResponseEntity<>(attachmentResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Attachment>> updateAttachment(@RequestBody Attachment attachment) {
        logger.info("Updating attachment by id: " + attachment.getAttachmentId());
        attachment = attachmentService.updateAttachment(attachment.getAttachmentId(), attachment);
        Resource<Attachment> attachmentResource = new Resource<>(attachment);
        attachmentResource.add(linkTo(methodOn(AttachmentController.class).findAttachmentById(attachment.getAttachmentId())).withSelfRel());
        return new ResponseEntity<>(attachmentResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Attachment> deleteAttachmentEverywhere(@PathVariable("id") Integer attachmentId) throws IOException {
        logger.info("Removing attachment by id: " + attachmentId);
        attachmentService.deleteAttachmentEverywhere(attachmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<Attachment> deleteAllAttachments() {
        logger.info("Removing all attachments.");
        attachmentService.deleteAllAttachments();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Resource<Attachment> getLink(Resource<Attachment> attachmentResource) {
        attachmentResource.add(linkTo(methodOn(AttachmentController.class)
                .findAttachmentById(attachmentResource.getContent().getAttachmentId()))
                .withSelfRel());
        return attachmentResource;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<PageCreator<Resource<Attachment>>> listAllAttachments(
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "asc", required = false) Boolean ascOrder) {
        logger.info("get all attachment by page number: " + pageNumber);
        Page<Attachment> attachmentsByPage = attachmentService.getAllAttachments(pageNumber, sortedBy, ascOrder);

        int currentPage = attachmentsByPage.getNumber() + 1;
        int begin = Math.max(1, currentPage - 5);
        int totalPages = attachmentsByPage.getTotalPages();
        int end = Math.min(currentPage + 5, totalPages);

        List<Resource<Attachment>> resourceList = new ArrayList<>();
        attachmentsByPage.forEach((attachment) -> resourceList.add(getLink(toResource(attachment))));

        PageCreator<Resource<Attachment>> pageCreator = new PageCreator<>();
        pageCreator.setRows(resourceList);
        pageCreator.setCurrentPage(Integer.valueOf(currentPage).toString());
        pageCreator.setBeginPage(Integer.valueOf(begin).toString());
        pageCreator.setEndPage(Integer.valueOf(end).toString());
        pageCreator.setTotalPages(Integer.valueOf(totalPages).toString());

        return new ResponseEntity<>(pageCreator, HttpStatus.OK);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Attachment>>> getAttachmentsByPath(
            @RequestParam(value = "path", required = true) String path) {
        logger.info("fetching attachment by search parameter: " + path);
        List<Attachment> attachmentsBySearchTerm = attachmentService.findAttachmentByPath(path);
        if (attachmentsBySearchTerm.isEmpty()) {
            logger.warn("no attachments were found");
        }
        List<Resource<Attachment>> resourceAttachmentList = new ArrayList<>();
        attachmentsBySearchTerm.stream().forEach((attachment) -> {
            resourceAttachmentList.add(getLink(toResource(attachment)));
        });
        return new ResponseEntity<>(resourceAttachmentList, HttpStatus.OK);
    }
}
