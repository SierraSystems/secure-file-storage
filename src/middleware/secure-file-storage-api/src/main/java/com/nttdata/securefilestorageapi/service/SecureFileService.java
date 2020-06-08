package com.nttdata.securefilestorageapi.service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SecureFileService {

    void uploadFile(MultipartFile multipartFile, boolean enablePublicReadAccess);
    void deleteFile(String fileName);
    byte[] getFile(String fileName);
}
