package com.utn.PhoneLines.service;

import com.utn.PhoneLines.exceptions.PhoneNotExistsException;
import com.utn.PhoneLines.exceptions.ResourceNotExistException;
import com.utn.PhoneLines.model.Call;
import com.utn.PhoneLines.model.City;
import com.utn.PhoneLines.model.Phone;
import com.utn.PhoneLines.model.Rate;
import com.utn.PhoneLines.model.dto.CallInfraestructure;
import com.utn.PhoneLines.repository.RateRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RateServiceTest {

    @Mock
    RateRepository rateRepository;

    @InjectMocks
    RateService rateService;

    @Before
    public void setUp() {
        initMocks(this);
    }


    private Rate createRate(){
        return Rate.builder()
                .idRate(1)
                .cityFrom(new City())
                .cityTo(new City())
                .costPrice(7)
                .salePrice(10)
                .build();
    }

    @Test
    public void getRatesOkTest(){
        List<Rate> list = new ArrayList<>();
        Rate r = createRate();
        list.add(r);
        when(this.rateRepository.findAll()).thenReturn(list);
        ResponseEntity<List<Rate>> response = this.rateService.getRates();
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getRatesbyCityOkTest() throws ResourceNotExistException {
        Integer idCityFrom = 1;
        Integer idCityTo = 2;
        Rate r = createRate();
        when(this.rateRepository.getRatesbyCity(idCityFrom, idCityTo)).thenReturn(r);
        ResponseEntity<Rate> response = ResponseEntity.ok(this.rateService.getRatesbyCity(idCityFrom, idCityTo));
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void addRateOkTest() {
        Rate r = createRate();
        when(this.rateRepository.save(r)).thenReturn(r);
        ResponseEntity<Rate> response = ResponseEntity.ok(this.rateService.add(r));
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }


    @Test
    public void getTariffByLocalityFromToOkTest() throws ResourceNotExistException {
        Rate r = createRate();
        Integer icCityFrom = 1;
        Integer idCityTo = 1;
        when(this.rateRepository.getRatesbyCity(icCityFrom,idCityTo)).thenReturn(r);
        Assert.assertEquals(r,this.rateService.getRatesbyCity(icCityFrom,idCityTo));
    }

}
