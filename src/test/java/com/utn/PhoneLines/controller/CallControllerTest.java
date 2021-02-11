package com.utn.PhoneLines.controller;

import com.utn.PhoneLines.exceptions.PhoneNotExistsException;
import com.utn.PhoneLines.exceptions.ResourceNotExistException;
import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.model.Call;
import com.utn.PhoneLines.model.Rate;
import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.dto.CallInfraestructure;
import com.utn.PhoneLines.model.dto.RangeDate;
import com.utn.PhoneLines.projection.CallClientOffice;
import com.utn.PhoneLines.projection.CallsClient;
import com.utn.PhoneLines.projection.CallsClientTop;
import com.utn.PhoneLines.projection.InvoiceUserAndDate;
import com.utn.PhoneLines.service.CallService;
import com.utn.PhoneLines.session.SessionManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallControllerTest {

    CallController controller;
    CallService service;
    SessionManager sessionManagerService;
    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Before
    public void setUp() {
        service = mock(CallService.class);
        sessionManagerService = mock(SessionManager.class);
        controller = new CallController(service,sessionManagerService);
    }

    DateFormat format = new SimpleDateFormat("YYYY-MM-dd", Locale.ENGLISH);

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

    private CallsClientTop createCallsClientTop() {
        return new CallsClientTop() {

            @Override
            public String getDestination() {
                return null;
            }

            @Override
            public String getAmount() {
                return null;
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
    public void getCallsOfUserByDateOk() throws ParseException, UserNotExistsException {
        Integer idUser = 1;
        String startDate = "2020-01-01";
        String finalDate = "2020-01-31";
        List<CallsClient> list = new ArrayList<>();
        list.add(createCallsClient());
        when(this.service.getCallsByUserByDate(new RangeDate(idUser,format.parse(startDate),format.parse(finalDate)))).thenReturn(list);
        ResponseEntity<List<CallsClient>> response = this.controller.getCallsOfUserByDate(new RangeDate(idUser,format.parse(startDate),format.parse(finalDate)));
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getTopDestination() throws ParseException, UserNotExistsException {
        Integer idUser = 1;
        String startDate = "2020-01-01";
        String finalDate = "2020-01-31";
        List<CallsClientTop> list = new ArrayList<>();
        list.add(createCallsClientTop());
        when(this.service.getTopDestination(idUser)).thenReturn(list);
        ResponseEntity<List<CallsClientTop>> response = this.controller.getTopDestination(new User().builder().idUser(1).build());
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getCallsByUserBackoffice() throws ParseException, UserNotExistsException {
        Integer idUser = 1;
        String startDate = "2020-01-01";
        String finalDate = "2020-01-31";
        List<CallClientOffice> list = new ArrayList<>();
        list.add(createCallClientOffice());
        when(this.service.getCallsByUser(idUser)).thenReturn(list);
        ResponseEntity<List<CallClientOffice>> response = this.controller.getCallsByUserBackoffice(idUser);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void addCallTest() throws PhoneNotExistsException, ParseException {
        CallInfraestructure r = new CallInfraestructure();
        Call c = new Call();
        when(this.service.addCall(r)).thenReturn(c);
        ResponseEntity<Call> response = ResponseEntity.ok(this.service.addCall(r));
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

}