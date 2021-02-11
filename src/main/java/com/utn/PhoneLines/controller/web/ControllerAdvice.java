package com.utn.PhoneLines.controller.web;

import com.utn.PhoneLines.exceptions.*;
import com.utn.PhoneLines.model.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.bind.ValidationException;

public class ControllerAdvice {




    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidLoginException.class)
    public ErrorResponseDto handleLoginException(InvalidLoginException exc) {
        return new ErrorResponseDto(401, "Invalid login");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorResponseDto handleValidationException(ValidationException exc) {
        return new ErrorResponseDto(400, exc.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotExistsException.class)
    public ErrorResponseDto handleUserNotExists() {
        return new ErrorResponseDto(403, "User not exists");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvoiceNotFoundException.class)
    public ErrorResponseDto handleInvoiceNotFoundException() {
        return new ErrorResponseDto(4, "Invoice not found");
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhoneAlreadyExistException.class)
    public ErrorResponseDto handlePhoneAlreadyExistException() {
        return new ErrorResponseDto(5, "Phone already exists");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FareNotExistsException.class)
    public ErrorResponseDto handleFareNotExistsException() {
        return new ErrorResponseDto(6, "fare no exists");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhoneNotExistsException.class)
    public ErrorResponseDto handlePhoneNotExistsException() {
        return new ErrorResponseDto(7, "Phone no exists");
    }



}
