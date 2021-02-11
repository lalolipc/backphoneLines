package com.utn.PhoneLines.service;

import com.utn.PhoneLines.model.City;
import com.utn.PhoneLines.model.State;
import com.utn.PhoneLines.repository.CityRepository;
import com.utn.PhoneLines.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StateService {
    private final StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

}
