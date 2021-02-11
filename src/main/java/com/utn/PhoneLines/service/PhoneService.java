package com.utn.PhoneLines.service;

import com.utn.PhoneLines.exceptions.PhoneAlreadyExistException;
import com.utn.PhoneLines.exceptions.PhoneNotExistsException;
import com.utn.PhoneLines.exceptions.ValidationException;
import com.utn.PhoneLines.model.Phone;
import com.utn.PhoneLines.model.PhoneType;
import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.dto.PhoneDto;
import com.utn.PhoneLines.model.dto.UpdatePhoneDto;
import com.utn.PhoneLines.model.enums.LineStatus;
import com.utn.PhoneLines.repository.PhoneRepository;
import com.utn.PhoneLines.repository.PhoneTypeRepository;
import com.utn.PhoneLines.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneService {

    private PhoneRepository phoneRepository;
    private PhoneTypeRepository phoneTypeRepository;
    private UserRepository userRepository;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository, PhoneTypeRepository phoneTypeRepository, UserRepository userRepository) {
        this.phoneRepository = phoneRepository;
        this.phoneTypeRepository = phoneTypeRepository;
        this.userRepository = userRepository;
    }

    public Phone add(PhoneDto phonedto) throws PhoneAlreadyExistException, ValidationException {


        Phone p = phoneRepository.findByNumber(phonedto.getNumber());
        Integer idTypePhone = phonedto.getIdPhoneType();

        User u = userRepository.getById(phonedto.getIdUser());
        PhoneType type = phoneTypeRepository.getById(idTypePhone);

        return phoneRepository.save(Phone.builder()
                .idPhone(0)
                .number(phonedto.getNumber())
                .phoneType(type)
                .city(null)
                .user(u).active(true).status(LineStatus.ENABLED)
                .build());

    }


    public Phone getById(Integer id) {

        return this.phoneRepository.getById(id);
    }

    public Phone getByPhoneNumber(String phoneNumber) {

        return this.phoneRepository.findByNumber(phoneNumber);
    }

    public Integer delete(Integer idPhone) throws PhoneNotExistsException {

        Phone search = phoneRepository.getById(idPhone);
        this.phoneRepository.deleteById(idPhone);
        return idPhone;

    }

    public Phone update(Phone phoneLine) {
        return this.phoneRepository.save(phoneLine);
    }

    public Phone changeStatusPhone( Phone p) throws PhoneNotExistsException {
        if(p.getStatus().equals(LineStatus.DISABLED.toString())) {
            p.setStatus(LineStatus.DISABLED);
        }else  {
            p.setStatus(LineStatus.ENABLED);
        }
        return this.phoneRepository.save(p);

    }
}
