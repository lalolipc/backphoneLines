package com.utn.PhoneLines.controller;

import com.utn.PhoneLines.controller.web.LoginController;
import com.utn.PhoneLines.exceptions.InvalidLoginException;
import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.UserType;
import com.utn.PhoneLines.model.dto.LoginInput;
import com.utn.PhoneLines.model.enums.UserTypeEnum;
import com.utn.PhoneLines.service.UserService;
import com.utn.PhoneLines.session.SessionManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class LoginControllerTest {

    LoginController controller;
    UserService service;
    SessionManager sessionManagerService;

    @Before
    public void setUp() {
        service = mock(UserService.class);
        sessionManagerService = mock(SessionManager.class);
        controller = new LoginController(service,sessionManagerService);
    }

    @Test
    public void loginOk() throws Exception, ValidationException, InvalidLoginException, UserNotExistsException {
        UserType userType = new UserType();
        userType.setName(UserTypeEnum.EMPLOYEE);
        User user = new User();
        user.setIdUser(1);
        user.setName("name");
        user.setLastName("lastName");
        user.setUserType(userType);

        LoginInput loginInput = new LoginInput();
        loginInput.setPassword("test");
        loginInput.setUserName("test");

        when(sessionManagerService.createSession(user)).thenReturn("token1234");
        when(service.getByUserNameAndPassword(loginInput)).thenReturn(user);
        ResponseEntity returnedToken= controller.login(loginInput);

        assertNotNull(returnedToken);

        verify(sessionManagerService, times(1)).createSession(user);
        verify(service, times(1)).getByUserNameAndPassword(loginInput);
    }

    @Test
    public void logoutOk() throws Exception, ValidationException, InvalidLoginException {

        String token="token1234";

        doNothing().when(sessionManagerService).removeSession(token);
        ResponseEntity returned= controller.logout(token);

        assertNotNull(returned);
        assertEquals(returned.getStatusCodeValue(), 200);

        verify(sessionManagerService, times(1)).removeSession(token);
    }
}