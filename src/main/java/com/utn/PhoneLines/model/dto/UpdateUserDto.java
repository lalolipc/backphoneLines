package com.utn.PhoneLines.model.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class UpdateUserDto {

    private Integer idcity;
    private String name;
    private String lastName;
    private String userName;
}