package com.team012.server.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DogCardDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        private String dogName;
        private String type;
        private String gender;
        private Integer age;
        private Double weight;
        private String snackMethod;
        private String bark;
        private String surgery;
        private String bowelTrained;
        private String etc;


//        {
//            "dogName" : "testDog",
//            "type" : "testType",
//            "gender" : "testGender",
//            "age" : 3,
//            "weight" : 15,
//            "snackMethod" : "testSnackMethod",
//            "bark" : "testBark",
//            "surgery" : "testSurgery",
//            "bowelTrained" : "testBowelTrained",
//            "etc" : "testEtc"
//        }
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Response {

        private Long id;
        private String photoImgUrl;
        private String dogName;
        private String type;
        private String gender;
        private Integer age;
        private Double weight;
        private String snackMethod;
        private String bark;
        private String surgery;
        private String bowelTrained;
        private String username;
        private String etc;
    }
}
