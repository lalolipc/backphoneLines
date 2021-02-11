package com.utn.PhoneLines.service;

import com.utn.PhoneLines.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTypeService {

    private final UserTypeRepository userTypeRepository;

    @Autowired

    public UserTypeService(UserTypeRepository userTypeRepository) {       this.userTypeRepository = userTypeRepository;    }
}
