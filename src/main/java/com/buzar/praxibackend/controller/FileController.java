package com.buzar.praxibackend.controller;

import com.buzar.praxibackend.entity.File;
import com.buzar.praxibackend.payload.UploadFileResponse;
import com.buzar.praxibackend.repository.FileRepository;
import com.buzar.praxibackend.repository.RealizationRepository;
import com.buzar.praxibackend.service.RealizationFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private RealizationFileService realizationFileService;

    @Autowired
    private RealizationRepository realizationRepositoryImpl;

    @Autowired
    FileRepository fileRepository;

    @GetMapping
    public List<File> finaAll() {

        return fileRepository.findAll();
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = realizationFileService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex){
            System.out.println("Could not determinate file type.");
        }

//        Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("{fileId}")
    public File findById(@PathVariable int fileId) {

        return fileRepository.findById(fileId);
    }

    @PostMapping("/uploadFile/real{realId}")
    public UploadFileResponse uploadFile(@PathVariable int realId,
                                         @RequestParam("file") MultipartFile file) {

        String fileName = realizationFileService.storeFile(file);
        File tempFile = new File();
        tempFile.setFileName(fileName);
        fileRepository.saveOrUpdate(tempFile);
        realizationRepositoryImpl.addFile(realId, tempFile.getFileId());
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse (fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles/real{realId}")
    public List<UploadFileResponse> uploadMultipleFiles(@PathVariable int realId,
                                                        @RequestParam("files") MultipartFile[] files) {

        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(realId, file))
                .collect(Collectors.toList());
    }

    @PutMapping("{fileId}")
    public void updateFile(@PathVariable int fileId,
                           @RequestBody File tempFile) {

        tempFile.setFileId(fileId);
        fileRepository.saveOrUpdate(tempFile);
    }

    @DeleteMapping("{fileId}")
    public void deleteFile(@PathVariable int fileId) {

        fileRepository.delete(fileId);
    }
}
