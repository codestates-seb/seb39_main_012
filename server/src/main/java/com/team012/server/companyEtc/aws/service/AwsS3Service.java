package com.team012.server.companyEtc.aws.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
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


    //s3로 파일 업로드 하고 url 리턴하는 메서드
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        validateFileExists(multipartFile);

        String fileName = originalFileName(multipartFile);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        try (InputStream inputStream = multipartFile.getInputStream()){
            byte[] bytes = IOUtils.toByteArray(inputStream);
            objectMetadata.setContentLength(bytes.length);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            amazonS3Client.putObject(new PutObjectRequest(bucketName,fileName,byteArrayInputStream,objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new FileUploadException();
        }
        String url = amazonS3Client.getUrl(bucketName, fileName).toString();

        return url;
    }

    //파일 이름 추출하는 메서드
    public String originalFileName(MultipartFile multipartFile) {
        return CommonUtils.buildFileName(multipartFile.getOriginalFilename());
    }

    //file 이름과 url 받아서 List<CompanyPostsImg> 로 리턴하는 메서드
    public List<CompanyPostsImg> convertCompanyPostImg(List<MultipartFile> multipartFileList) {
        if (multipartFileList.size() > 5) {
            throw new RuntimeException("Image uploads are limited to 5");
        }
        List<CompanyPostsImg> fileList = new ArrayList<>();

        multipartFileList.forEach(file -> {

            String url;
            try {
                url = uploadFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String fileName = originalFileName(file);

            CompanyPostsImg companyPostsImg = CompanyPostsImg.builder()
                    .fileName(fileName)
                    .imgUrl(url)
                    .build();
            fileList.add(companyPostsImg);
        });
        return fileList;
    }

    //List<CompanyPostsImg> 수정 메서드(기존 s3에 있는 파일 삭제 후 추가) s3에 기존 파일이 삭제되지 않는 에러가 있음 추후 수정 예정
    public List<CompanyPostsImg> reviseFileV1(List<CompanyPostsImg> imgList,List<MultipartFile> multipartFileList) {
        List<String> list = imgList.stream().map(CompanyPostsImg::getFileName).collect(Collectors.toList());
        for(int i = 0; i< list.size(); i++) { //기존 파일 삭제
            String currentFilePath = list.get(i);
            if(!"".equals(currentFilePath) && currentFilePath != null) {
                boolean isExistObject = amazonS3Client.doesObjectExist(bucketName, currentFilePath);

                if(isExistObject) {
                    amazonS3Client.deleteObject(bucketName, currentFilePath);
                }
            }
        }
        return  convertCompanyPostImg(multipartFileList);

    }

    //s3에 업로드된 파일 삭제 메서드
    public void deleteFile(List<CompanyPostsImg> imgList) {
        List<String> fileNameList = imgList.stream().map(CompanyPostsImg::getFileName).collect(Collectors.toList());
        for(String file : fileNameList) {
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, file));
        }

    }

    //s3에 업로드 할 사진 validation 메서드
    private  void validateFileExists(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) {
            throw new RuntimeException("EmptyFileException()");
        }
    }
}
