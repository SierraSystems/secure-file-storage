package com.nttdata.securefilestorageapi.service.impl;


import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.nttdata.securefilestorageapi.service.SecureFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SecureFileServiceImpl implements SecureFileService
{
    private String awsS3Bucket;
    private AmazonS3 amazonS3;
    private static final Logger logger = LoggerFactory.getLogger(SecureFileServiceImpl.class);

    @Autowired
    public SecureFileServiceImpl(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider, String awsS3Bucket)
    {
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion.getName()).build();
        this.awsS3Bucket = awsS3Bucket;
    }

    @Async
    public void uploadFile(MultipartFile multipartFile, boolean enablePublicReadAccess)
    {
        String fileName = multipartFile.getOriginalFilename();

        try {
            //creating the file in the server (temporarily)
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();

            PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3Bucket, fileName, file);

            if (enablePublicReadAccess) {
                putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
            }
            this.amazonS3.putObject(putObjectRequest);
            //removing the file created in the server
            file.delete();
        } catch (IOException | AmazonServiceException ex) {
            logger.error("error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ");
        }
    }

    @Async
    public void deleteFile(String fileName)
    {
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(awsS3Bucket, fileName));
        } catch (AmazonServiceException ex) {
            logger.error("error [" + ex.getMessage() + "] occurred while removing [" + fileName + "] ");
        }
    }

    @Async
    public byte[] getFile(String fileName)
    {
        byte[] content = null;
        try {
            logger.info("Downloading an object with key= " + fileName);
            final S3Object s3Object = amazonS3.getObject(awsS3Bucket, fileName);
            final S3ObjectInputStream stream = s3Object.getObjectContent();

            content = IOUtils.toByteArray(stream);
            logger.info("File downloaded successfully.");
            s3Object.close();

        } catch(final IOException ex) {
            logger.info("IO Error Message= " + ex.getMessage());
        }
        return content;
    }
}
