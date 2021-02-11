package com.utn.PhoneLines.session;

import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.enums.UserTypeEnum;

import java.util.Date;

public class Session {

    String      token;
    User loggedUser;
    Date        lastAction;

    public Session(String token, User loggedUser, Date lastAction) {
        this.token = token;
        this.loggedUser = loggedUser;
        this.lastAction = lastAction;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Date getLastAction() {
        return lastAction;
    }

    public void setLastAction(Date lastAction) {
        this.lastAction = lastAction;
    }
//agregado tipo  empleado
    public boolean isEmployee(){ return loggedUser.getUserType().getName() == UserTypeEnum.EMPLOYEE;}
}
