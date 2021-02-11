package com.utn.PhoneLines.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginInput {
    String password;
    String userName;



    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}