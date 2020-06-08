package com.nttdata.securefilestorageapi.controller;

import com.nttdata.securefilestorageapi.service.SecureFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private SecureFileService secureFileService;

    @PostMapping
    public Map<String, String> uploadFile(@RequestPart(value = "file") MultipartFile file)
    {
        this.secureFileService.uploadFile(file, false);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + file.getOriginalFilename() + "] uploading request submitted successfully.");

        return response;
    }

    @DeleteMapping
    public Map<String, String> deleteFile(@RequestParam("file_name") String fileName)
    {
        this.secureFileService.deleteFile(fileName);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + fileName + "] removing request submitted successfully.");

        return response;
    }

    @GetMapping
    public ResponseEntity<ByteArrayResource> getFile(@RequestParam("file_name") String fileName)
    {
        final byte[] data = this.secureFileService.getFile(fileName);

        final ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}
