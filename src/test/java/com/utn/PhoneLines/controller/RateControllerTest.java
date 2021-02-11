package com.utn.PhoneLines.controller;

import com.utn.PhoneLines.exceptions.ResourceNotExistException;
import com.utn.PhoneLines.model.City;
import com.utn.PhoneLines.model.Rate;
import com.utn.PhoneLines.service.RateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RateControllerTest {
    @Mock
    RateService rateServiceMock;

    @InjectMocks
    RateController rateController;

    @Before
    public void setUp(){initMocks(this);}

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
    public void getTariffOkTest(){
        List<Rate> list = new ArrayList<>();
        list.add(createRate());
        when(this.rateServiceMock.getRates()).thenReturn(ResponseEntity.ok(list));
        ResponseEntity<List<Rate>> response = this.rateController.getRates();
        Assert.assertEquals(list.size(),response.getBody().size());
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }


    @Test
    public void getRatesbyCityOkTest() throws ResourceNotExistException {
        Integer idCityFrom = 1;
        Integer idCityTo = 2;
        Rate r = createRate();
        when(this.rateServiceMock.getRatesbyCity(idCityFrom, idCityTo)).thenReturn(r);
        ResponseEntity<Rate> response = this.rateController.getRatesbyCity(idCityFrom, idCityTo);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

}
