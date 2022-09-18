package com.team012.server.address;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class Address {
    private String latitude;
    private String longitude;

    @Builder
    public Address(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
