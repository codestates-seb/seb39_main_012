package com.team012.server.companyEtc.aws.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.team012.server.companyEtc.aws.utils.CommonUtils;
import com.team012.server.companyEtc.entity.CompanyPostsImg;
import com.team012.server.companyEtc.repository.CompanyPostsImgRepository;
import com.team012.server.companyPosts.entity.CompanyPosts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsS3Service {

    private final AmazonS3Client amazonS3Client;


    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;



    public String uploadFileV1(MultipartFile multipartFile) throws IOException {
        validateFileExists(multipartFile);

        String fileName = CommonUtils.buildFileName(multipartFile.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        try (InputStream inputStream = multipartFile.getInputStream()){
            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new FileUploadException();
        }
        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }

    public List<CompanyPostsImg> uploadFilesV2(List<MultipartFile> multipartFileList) {
        if (multipartFileList.size() > 5) {
            throw new RuntimeException("Image uploads are limited to 5");
        }
        List<CompanyPostsImg> fileList = new ArrayList<>();

        multipartFileList.forEach(file -> {
            validateFileExists(file);

            String fileName = CommonUtils.buildFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());

            try (InputStream inputStream = file.getInputStream()){
                byte[] bytes = IOUtils.toByteArray(inputStream);
                objectMetadata.setContentLength(bytes.length);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                amazonS3Client.putObject(new PutObjectRequest(bucketName,fileName,byteArrayInputStream,objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String url = amazonS3Client.getUrl(bucketName, fileName).toString();

            CompanyPostsImg companyPostsImg = CompanyPostsImg.builder()
                    .fileName(fileName)
                    .imgUrl(url)
                    .build();
            fileList.add(companyPostsImg);
        });
        return fileList;
    }

    private  void validateFileExists(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) {
            throw new RuntimeException("EmptyFileException()");
        }
    }
    public List<CompanyPostsImg> reviseFileV1(List<CompanyPostsImg> imgList,List<MultipartFile> multipartFileList, CompanyPosts companyPosts) {
        List<String> list = imgList.stream().map(CompanyPostsImg::getFileName).collect(Collectors.toList());
        for(int i = 0; i< list.size(); i++) { //기존 파일 삭제
            String currentFilePath = list.get(i);
            if(!"".equals(currentFilePath) && currentFilePath != null) {
                boolean isExistObject = amazonS3Client.doesObjectExist(bucketName, currentFilePath);

                if(isExistObject) {
                    amazonS3Client.deleteObject(bucketName, currentFilePath);
                }
            }
//            imgRepository.delete(imgList.get(i));
        }
        return  uploadFilesV2(multipartFileList);

    }

}
