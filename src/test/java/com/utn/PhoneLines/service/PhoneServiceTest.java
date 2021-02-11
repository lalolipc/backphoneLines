package com.utn.PhoneLines.service;

import com.utn.PhoneLines.exceptions.PhoneAlreadyExistException;
import com.utn.PhoneLines.exceptions.PhoneNotExistsException;
import com.utn.PhoneLines.exceptions.ValidationException;
import com.utn.PhoneLines.model.*;
import com.utn.PhoneLines.model.dto.PhoneDto;
import com.utn.PhoneLines.repository.InvoiceRepository;
import com.utn.PhoneLines.repository.PhoneRepository;
import com.utn.PhoneLines.repository.PhoneTypeRepository;
import com.utn.PhoneLines.repository.UserRepository;
import io.swagger.models.auth.In;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PhoneServiceTest {
    @Mock
    PhoneRepository phoneRepository;

    @Mock
    PhoneTypeRepository phoneTypeRepository;


    @Mock
    UserRepository userRepository;

    @InjectMocks
    PhoneService phoneService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    private Phone createPhone(){
        return Phone.builder()
                .idPhone(1)
                .number("123")
                .build();
    }

    @Test
    public void addPhoneOkTest() throws PhoneAlreadyExistException, ValidationException {
        Phone r = createPhone();
        PhoneDto dto = new PhoneDto();
        dto.setNumber("123");
        dto.setIdPhoneType(1);
        PhoneType ptype = new PhoneType();
        when(this.phoneRepository.save(r)).thenReturn(r);
        when(phoneRepository.findByNumber(r.getNumber())).thenReturn(r);
        when(phoneTypeRepository.getById(1)).thenReturn(ptype);
        ResponseEntity<Phone> response = ResponseEntity.ok(this.phoneService.add(dto));
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getByIdOkTest(){
        Integer idUser = 1;
        Phone phone = createPhone();
        when(this.phoneRepository.getById(idUser)).thenReturn(phone);
        ResponseEntity<Phone> response = ResponseEntity.ok(this.phoneService.getById(idUser));
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getByPhoneNumberOkTest(){
        String phoneNumber = "123";
        Phone phone = createPhone();
        when(this.phoneRepository.findByNumber(phoneNumber)).thenReturn(phone);
        ResponseEntity<Phone> response = ResponseEntity.ok(this.phoneService.getByPhoneNumber(phoneNumber));
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void deleteOkTest() throws PhoneNotExistsException {
        Integer idUser = 1;
        Integer idPhoneLine = 1;
        Phone phone = createPhone();
        when(this.phoneRepository.getById(idUser)).thenReturn(phone);
        Integer reply = this.phoneService.delete(idPhoneLine);
        Assert.assertEquals(reply, idPhoneLine);
    }


}

