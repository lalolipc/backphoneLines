package com.utn.PhoneLines.controller;

import com.utn.PhoneLines.exceptions.*;
import com.utn.PhoneLines.model.Phone;
import com.utn.PhoneLines.model.dto.PhoneDto;
import com.utn.PhoneLines.model.dto.UpdatePhoneDto;
import com.utn.PhoneLines.model.enums.LineStatus;
import com.utn.PhoneLines.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/phone")
public class PhoneController {
    private final PhoneService phoneService;

    @Autowired

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    //ok
    public Phone addPhone(PhoneDto phone) throws ValidationException, PhoneAlreadyExistException {
        return phoneService.add(phone);
    }
//ok
    public ResponseEntity<Phone> changeStatus(UpdatePhoneDto updatePhoneDto) throws PhoneNotExistsException {

   // if(status.equals(LineStatus.DISABLED) || status.equals(LineStatus.ENABLED) )

        Phone p=this.phoneService.getByPhoneNumber(updatePhoneDto.getNumber());


        this.phoneService.changeStatusPhone(p);
        return ResponseEntity.ok().build();





    }
//ok

    public ResponseEntity delete(Integer idPhone) throws PhoneNotExistsException {

        this.phoneService.delete(idPhone);
        return ResponseEntity.ok().build();
    }
    //ok
    public ResponseEntity<Phone> getPhoneLineByNumber(String number) throws PhoneNotExistsException {
        return ResponseEntity.ok(this.phoneService.getByPhoneNumber(number));
    }
}
