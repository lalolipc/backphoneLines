package com.utn.PhoneLines.service;

import com.utn.PhoneLines.repository.PhoneTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneTypeService {

    private final PhoneTypeRepository phoneTypeRepository;

    @Autowired

    public PhoneTypeService(PhoneTypeRepository phoneTypeRepository) {
        this.phoneTypeRepository = phoneTypeRepository;
    }
}
