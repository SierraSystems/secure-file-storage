package com.nttdata.securefilestorageapi.controller;

import com.nttdata.securefilestorageapi.service.SecureFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3085")
public class FileController {

    @Autowired
    private SecureFileService secureFileService;

    @PostMapping(value="/files")
    public Map<String, String> uploadFile(@RequestPart(value = "file") MultipartFile file)
    {
        this.secureFileService.uploadFile(file, false);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + file.getOriginalFilename() + "] uploading request submitted successfully.");

        return response;
    }

    @DeleteMapping(value="/files/{filename:.+}")
    public Map<String, String> deleteFile(@PathVariable String filename)
    {
        this.secureFileService.deleteFile(filename);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + filename + "] removing request submitted successfully.");

        return response;
    }

    @GetMapping(value="/files/{filename:.+}")
    public ResponseEntity<ByteArrayResource> getFile(@PathVariable String filename)
    {
        final byte[] data = this.secureFileService.getFile(filename);

        final ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

    @GetMapping(value="/files")
    public List<String> getFileNames()
    {
        List<String> fileNames = this.secureFileService.getFileNames();
        return fileNames;
    }
}
