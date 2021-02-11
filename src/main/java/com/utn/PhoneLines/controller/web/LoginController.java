package com.utn.PhoneLines.controller.web;

import com.utn.PhoneLines.exceptions.InvalidLoginException;
import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.dto.LoginInput;
import com.utn.PhoneLines.service.UserService;
import com.utn.PhoneLines.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/")
public class LoginController {

    UserService userService;
    SessionManager sessionManager;

    @Autowired
    public LoginController(UserService userService, SessionManager sessionManager) {
        this.userService = userService;
        this.sessionManager = sessionManager;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginInput loginInput) throws InvalidLoginException, ValidationException {
        ResponseEntity response;
        try {
            User u = userService.getByUserNameAndPassword(loginInput);
            if(u!=null) {
                String token = sessionManager.createSession(u);
                response = ResponseEntity.ok().headers(createHeaders(token)).build();
            }else{
                response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception | UserNotExistsException e) {
            throw new InvalidLoginException(e);
        }
        return response;
    }


    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {
        sessionManager.removeSession(token);
        return ResponseEntity.ok().build();
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);
        return responseHeaders;
    }
}