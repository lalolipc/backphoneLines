package com.utn.PhoneLines.service;

import com.utn.PhoneLines.exceptions.PhoneNotExistsException;
import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.exceptions.ValidationException;
import com.utn.PhoneLines.model.*;
import com.utn.PhoneLines.model.dto.CallInfraestructure;
import com.utn.PhoneLines.model.dto.RangeDate;
import com.utn.PhoneLines.model.enums.UserTypeEnum;
import com.utn.PhoneLines.projection.CallClientOffice;
import com.utn.PhoneLines.projection.CallsClient;
import com.utn.PhoneLines.projection.CallsClientTop;
import com.utn.PhoneLines.projection.InvoiceUserAndDate;
import com.utn.PhoneLines.repository.CallRepository;
import com.utn.PhoneLines.repository.PhoneRepository;
import com.utn.PhoneLines.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallServiceTest {

    @Mock
    CallRepository callRepositoryMockito;

    @Mock
    PhoneService phoneServiceMockito;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    CallService callService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    private User createUser() {
        return User.builder()
                .idUser(1)
                .password("123")
                .name("Rocio")
                .lastName("Sanchez")
                .userType(new UserType(1, UserTypeEnum.CLIENT))
                .userName("Rocio")
                .build();
    }

    private Call createCall() {
        return Call.builder()
                .idCall(1)
                .invoice(new Invoice())
                .numberOrigin("123")
                .numberDestination("321")
                .dateCall(new Date())
                .duration(22)
                .costPrice((float) 2.2)
                .totalPrice((float) 5.4)
                .build();
    }

    private CallInfraestructure createCallInfraestructure() {
        return CallInfraestructure.builder()
                .numberOrigin("123")
                .numberDestination("321")
                .duration(22)
                .callDate("2020-01-01 00:00:00")
                .build();
    }

    private CallsClientTop createCallsClientTop() {
        return new CallsClientTop() {

            @Override
            public String getDestination() {
                return "123";
            }

            @Override
            public String getAmount() {
                return "10.00";
            }
        };
    }

    private CallsClient createCallsClient() {
        return new CallsClient() {
            @Override
            public String getNumberorigin() {
                return "123";
            }

            @Override
            public String getNumberdestination() {
                return "321";
            }

            @Override
            public String getCitydestination() {
                return "";
            }

            @Override
            public LocalDateTime getDatecall() {
                return LocalDateTime.now();
            }

            @Override
            public Integer getDuration() {
                return 22;
            }

            @Override
            public double getTotalprice() {
                return 10.0;
            }

            @Override
            public String getCityDestination() {
                return "";
            }

            @Override
            public String getCityorigin() {
                return "";
            }
        };
    }

    private CallClientOffice createCallClientOffice() {
        return new CallClientOffice() {

            @Override
            public String getNumberorigin() {
                return "123";
            }

            @Override
            public String getCitydestination() {
                return "";
            }

            @Override
            public String getCityorigin() {
                return "";
            }

            @Override
            public String getNumberdestination() {
                return "";
            }

            @Override
            public String getCityDestination() {
                return "";
            }

            @Override
            public Integer getDuration() {
                return 22;
            }

            @Override
            public LocalDateTime getDatecall() {
                return LocalDateTime.now();
            }

            @Override
            public double getTotalprice() {
                return 10.5;
            }

            @Override
            public double getCostprice() {
                return 5.25;
            }

            @Override
            public double getSaleprice() {
                return 5.0;
            }
        };
    }

    @Test
    public void getCallsByUserOkTest() throws UserNotExistsException {
        Integer id = 1;
        List<CallClientOffice> list = new ArrayList<>();
        list.add(createCallClientOffice());
        when(callRepositoryMockito.getCallsByUserBackoffice(id)).thenReturn(list);
        ResponseEntity<List<CallClientOffice>> response = ResponseEntity.ok(this.callService.getCallsByUser(id));

    }

    @Test
    public void getCallsByUserByDate() throws UserNotExistsException {
        List<CallsClient> list = new ArrayList<>();
        list.add(createCallsClient());
        RangeDate rgDate = new RangeDate().builder().idUser(1).dateFrom(new Date()).dateTo(new Date()).build();
        when(callRepositoryMockito.getReportCallsByUserByDate(rgDate.getIdUser(),rgDate.getDateFrom(),rgDate.getDateTo())).thenReturn(list);
        ResponseEntity<List<CallsClient>> response = ResponseEntity.ok(this.callService.getCallsByUserByDate(rgDate));
    }

    @Test
    public void getTopDestination() throws UserNotExistsException {
        List<CallsClientTop> list = new ArrayList<>();
        list.add(createCallsClientTop());
        Integer id = 1;
        when(callRepositoryMockito.getTopCallsbyUser(id)).thenReturn(list);
        ResponseEntity<List<CallsClientTop>> response = ResponseEntity.ok(this.callService.getTopDestination(id));
    }

    @Test
    public void addCallOkTest() throws PhoneNotExistsException, ParseException {
        CallInfraestructure callInfra = createCallInfraestructure();
        Call call = createCall();//phoneService.getByPhoneNumber   phoneRepositoryMockito
        when(this.phoneServiceMockito.getByPhoneNumber(callInfra.getNumberDestination())).thenReturn(new Phone());
        when(this.phoneServiceMockito.getByPhoneNumber(callInfra.getNumberOrigin())).thenReturn(new Phone());
        when(this.callRepositoryMockito.save(call)).thenReturn(call);
        ResponseEntity<Call> response = ResponseEntity.ok(this.callService.addCall(callInfra));
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }


}
