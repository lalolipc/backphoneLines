package com.utn.PhoneLines.service;

import com.utn.PhoneLines.exceptions.PhoneNotExistsException;
import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.model.*;
import com.utn.PhoneLines.model.dto.CallInfraestructure;
import com.utn.PhoneLines.model.dto.RangeDate;
import com.utn.PhoneLines.projection.CallClientOffice;
import com.utn.PhoneLines.projection.CallsClient;
import com.utn.PhoneLines.projection.CallsClientTop;
import com.utn.PhoneLines.repository.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CallService {

    private CallRepository callRepository;
    private PhoneService phoneService;
    @Autowired
    public CallService(CallRepository callRepository, PhoneService phoneService) {
        this.callRepository = callRepository;
        this.phoneService = phoneService;
    }

    public List<CallsClient> getCallsByUserByDate(RangeDate rangeDate) throws UserNotExistsException {

        return callRepository.getReportCallsByUserByDate(rangeDate.getIdUser(), rangeDate.getDateFrom(), rangeDate.getDateTo());
    }

    public List<CallsClientTop> getTopDestination(Integer idUser) throws UserNotExistsException {

        return callRepository.getTopCallsbyUser(idUser);

    }

    public List<CallClientOffice> getCallsByUser(Integer idUser) throws UserNotExistsException {
        return callRepository.getCallsByUserBackoffice(idUser);
    }

    public Call addCall(CallInfraestructure callInfra) throws PhoneNotExistsException, ParseException {
        Phone NumOrigin = phoneService.getByPhoneNumber(callInfra.getNumberOrigin());
        Phone NumDestination = phoneService.getByPhoneNumber(callInfra.getNumberDestination());

        return callRepository.save(Call.builder()
                .idCall(0)
                .originPhone(NumOrigin)
                .destinationPhone(NumDestination)
                .dateCall(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(callInfra.getCallDate()))
                .duration(callInfra.getDuration())
                .totalPrice(0)
                .costPrice(0)
                .salePrice(0)
                .numberOrigin(callInfra.getNumberOrigin())
                .numberDestination(callInfra.getNumberDestination())
                .cityOrigin(null)
                .cityDestination(null)
                .build());
    }

}
