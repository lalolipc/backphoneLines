package com.utn.PhoneLines.model.dto;

import lombok.Data;

@Data
public class NewUserDTO {

    private Integer idCity;

    private String name;

    private String lastname;

    private String username;

    private String idNumber;

    private String userType;

    private Boolean active;
}