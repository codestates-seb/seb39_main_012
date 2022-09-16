package com.team012.server.companyPosts.converter;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ListToString {

    public String listToString(List<String> tagList) {
        return String.join(",",tagList);
    }

    public List<String> stringToList(String tags) {
        String[] arr = tags.split(",");

        return Arrays.asList(arr);
    }
}
