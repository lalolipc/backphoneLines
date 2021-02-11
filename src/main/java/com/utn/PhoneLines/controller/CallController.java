package com.utn.PhoneLines.controller;

import com.utn.PhoneLines.exceptions.PhoneNotExistsException;
import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.model.Call;
import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.dto.CallInfraestructure;
import com.utn.PhoneLines.model.dto.RangeDate;
import com.utn.PhoneLines.projection.CallClientOffice;
import com.utn.PhoneLines.projection.CallsClient;
import com.utn.PhoneLines.projection.CallsClientTop;
import com.utn.PhoneLines.service.CallService;
import com.utn.PhoneLines.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.utn.PhoneLines.exceptions.ValidationException;

import java.text.ParseException;
import java.util.List;

@Controller

@RequestMapping("/call")
public class CallController {

    private final CallService callService;
    private final SessionManager sessionManager;

    @Autowired
    public CallController(CallService callService, SessionManager sessionManager) {
        this.callService = callService;
        this.sessionManager = sessionManager;
    }



    //le saque el requestbody que decia de range
    public  ResponseEntity<List<CallsClient>> getCallsOfUserByDate(RangeDate rangeDate) throws UserNotExistsException {
        List<CallsClient> listtCalls = callService.getCallsByUserByDate(rangeDate);
        return listtCalls.size()>0 ? ResponseEntity.ok(listtCalls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
//le saque el requestbody que decia de user
    public ResponseEntity<List<CallsClientTop>> getTopDestination(User currentUser)throws UserNotExistsException {


        List<CallsClientTop> listtCalls =  callService.getTopDestination(currentUser.getIdUser());
        return listtCalls.size()>0 ? ResponseEntity.ok(listtCalls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<List<CallClientOffice>> getCallsByUserBackoffice(Integer idUser) throws UserNotExistsException{

        List<CallClientOffice> listtCalls = callService.getCallsByUser(idUser);
        return listtCalls.size()>0 ? ResponseEntity.ok(listtCalls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



    public Call addCall(CallInfraestructure callInfra) throws PhoneNotExistsException, ParseException {
        return callService.addCall(callInfra);
    }
   }




