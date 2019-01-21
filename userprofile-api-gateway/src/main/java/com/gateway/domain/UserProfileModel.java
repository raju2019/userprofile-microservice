package com.gateway.domain;

import lombok.Data;

import java.util.Date;


@Data
public class UserProfileModel {

    public Long userId;

    public String alias;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String homeAddress;

    private String officeAddress;

    private String email;

    private String postcode;

    private String country;

}
