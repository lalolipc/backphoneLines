package com.utn.PhoneLines.controller.web;

import com.utn.PhoneLines.controller.CallController;
import com.utn.PhoneLines.exceptions.PhoneNotExistsException;
import com.utn.PhoneLines.exceptions.ValidationException;
import com.utn.PhoneLines.model.dto.CallInfraestructure;
import com.utn.PhoneLines.utils.locationUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.ParseException;

@RestController
@RequestMapping("/infrastructure")
public class InfraestructureController {

    CallController callController;

    @Autowired
    public InfraestructureController(CallController callController) {
        this.callController = callController;
    }


        @PostMapping("/")
    public ResponseEntity addCall(@RequestHeader("Authorization") String sessionToken, @RequestBody CallInfraestructure call) throws ValidationException {

        ResponseEntity responseEntity;

        try {
            URI uri = locationUri.getLocation(this.callController.addCall(call).getIdCall());
            responseEntity = ResponseEntity.created(uri).build();
        } catch (PhoneNotExistsException | ParseException e) {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return responseEntity;

    }

}
