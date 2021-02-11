package com.utn.PhoneLines.controller;

import com.utn.PhoneLines.exceptions.UserException;
import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.model.Invoice;
import com.utn.PhoneLines.model.Phone;
import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.dto.RangeDate;
import com.utn.PhoneLines.projection.InvoiceUserAndDate;
import com.utn.PhoneLines.service.InvoiceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class InvoiceControllerTest {

    @Mock
    InvoiceService invoiceServiceMockito;

    @InjectMocks
    InvoiceController invoiceController;

    @Before
    public void setUp(){initMocks(this);}

    DateFormat format = new SimpleDateFormat("YYYY-MM-dd", Locale.ENGLISH);


    private InvoiceUserAndDate createInvoiceUserAndDate() {
        return new InvoiceUserAndDate() {
            @Override
            public String getNumber() {
                return "123";
            }

            @Override
            public Integer getCallsamount() {
                return 1;
            }

            @Override
            public double getTotalprice() {
                return 10.0;
            }

            @Override
            public boolean getPaid() {
                return false;
            }

            @Override
            public LocalDateTime getDuedate() {
                return LocalDateTime.now();
            }

            @Override
            public LocalDateTime getDateinvoice() {
                return LocalDateTime.now();
            }
        };
    }

    private Invoice createInvoice() {
        return Invoice.builder()
                .idInvoice(1)
                .phone(new Phone())
                .callsAmount(1)
                .costPrice((float) 2.3)
                .totalPrice((float) 5)
                .dateInvoice(LocalDateTime.now())
                .dueDate(LocalDateTime.now())
                .build();
    }

    @Test
    public void getBillsBtwDatesByIdUserTest() throws UserException, ParseException, UserNotExistsException {
        Integer idUser = 1;
        String startDate = "2020-01-01";
        String finalDate = "2020-01-31";
        List<InvoiceUserAndDate> list = new ArrayList<>();
        list.add(createInvoiceUserAndDate());
        when(this.invoiceServiceMockito.getInvoicesByUserByDate(new RangeDate(idUser,format.parse(startDate),format.parse(finalDate)))).thenReturn(ResponseEntity.ok(list));
        ResponseEntity<List<InvoiceUserAndDate>> response = this.invoiceController.getInvoicesBtwDates(new RangeDate(idUser,format.parse(startDate),format.parse(finalDate)));
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void findAllOkTest() throws UserException, UserNotExistsException {
        Integer idUser = 1;
        List<Invoice> list = new ArrayList<>();
        Invoice bill = createInvoice();
        list.add(bill);
        when(this.invoiceServiceMockito.getBillsByIdUser(idUser)).thenReturn(ResponseEntity.ok(list));
        ResponseEntity<List<Invoice>> response = this.invoiceController.findAll(idUser);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }


    /*
     public ResponseEntity<List<Invoice>> findAll(@RequestHeader("Authorization") String token) throws UserNotExistsException, UserException {

        ResponseEntity<List<Invoice>> listInvoices = invoiceService.getBillsByIdUser(currentUser.getIdUser());
        return listInvoices.getBody().size()>0 ? listInvoices : ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
     */

}
