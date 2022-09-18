package com.team012.server.companyPosts.converter;

import com.team012.server.address.Address;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListToString {

    public String listToString(List<String> tagList) {
        return String.join(",",tagList);
    }


    public Address ListToAddress(List<String> list) {
        String latitude = list.get(0);
        String longitude = list.get(0);

        return Address.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }

}
