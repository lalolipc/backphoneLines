package com.utn.PhoneLines.service;

import com.utn.PhoneLines.exceptions.ResourceNotExistException;
import com.utn.PhoneLines.model.Phone;
import com.utn.PhoneLines.model.Rate;
import com.utn.PhoneLines.repository.PhoneRepository;
import com.utn.PhoneLines.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RateService {
    private final RateRepository rateRepository;

    @Autowired
    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public Rate add(Rate rate) {
        return rateRepository.save(rate);
    }

    public ResponseEntity<List<Rate>> getRates() {

        List<Rate> listRates = rateRepository.findAll();

        if(!listRates.isEmpty()){
            return ResponseEntity.ok(listRates);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    public Rate getRatesbyCity(Integer idCityFrom, Integer idCityTo) throws ResourceNotExistException {
        Rate rate = rateRepository.getRatesbyCity(idCityFrom, idCityTo);

        return  Optional.ofNullable(rate).orElseThrow(() -> new ResourceNotExistException("Tariff do not exists"));
    }

}

