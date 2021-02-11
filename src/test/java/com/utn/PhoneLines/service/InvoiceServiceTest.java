package com.utn.PhoneLines.service;

import com.utn.PhoneLines.exceptions.UserException;
import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.model.Invoice;
import com.utn.PhoneLines.model.Phone;
import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.UserType;
import com.utn.PhoneLines.model.dto.RangeDate;
import com.utn.PhoneLines.model.enums.UserTypeEnum;
import com.utn.PhoneLines.projection.InvoiceUserAndDate;
import com.utn.PhoneLines.repository.InvoiceRepository;
import com.utn.PhoneLines.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class InvoiceServiceTest {

    @Mock
    InvoiceRepository invoiceRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    InvoiceService invoiceService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    DateFormat format = new SimpleDateFormat("YYYY-MM-dd", Locale.ENGLISH);

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
    public void getBillsByIdUserOkTest() throws UserException, ParseException, UserNotExistsException {
        Integer id = 1;
        User u = createUser();
        List<Invoice> list = new ArrayList<>();
        Invoice bill = createInvoice();
        list.add(bill);
        when(this.userRepository.getById(id)).thenReturn(u);
        when(this.invoiceRepository.getInvoicesByIdUser(id)).thenReturn(list);
        ResponseEntity<List<Invoice>> response = this.invoiceService.getBillsByIdUser(id);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getInvoicesBtwDatesByIdUserOkTest() throws UserException, ParseException, UserNotExistsException {
        String startDate = "2020-01-01";
        String finalDate = "2020-01-31";
        Integer id = 1;
        User u = createUser();
        List<InvoiceUserAndDate> list = new ArrayList<>();
        InvoiceUserAndDate invoice = createInvoiceUserAndDate();
        list.add(invoice);
        when(this.userRepository.getById(id)).thenReturn(u);
        when(this.invoiceRepository.getReportInvoicesByUserByDate(id,format.parse(startDate),format.parse(finalDate))).thenReturn(list);
        ResponseEntity<List<InvoiceUserAndDate>> response = (ResponseEntity<List<InvoiceUserAndDate>>) this.invoiceService.getInvoicesByUserByDate(new RangeDate(id,format.parse(startDate),format.parse(finalDate)));
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getBillsBtwDatesByIdUserEmptyBillTest() throws UserException, ParseException, UserNotExistsException {
        String startDate = "2020-01-01";
        String finalDate = "2020-01-31";
        Integer id = 1;
        User u = createUser();
        List<InvoiceUserAndDate> list = new ArrayList<>();
        when(this.userRepository.getById(id)).thenReturn(u);
        when(this.invoiceRepository.getReportInvoicesByUserByDate(id,format.parse(startDate),format.parse(finalDate))).thenReturn(list);
        ResponseEntity<List<InvoiceUserAndDate>> response = this.invoiceService.getInvoicesByUserByDate(new RangeDate(id,format.parse(startDate),format.parse(finalDate)));
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test(expected = UserNotExistsException.class)
    public void getBillsBtwDatesByIdUserNullTest() throws ParseException, UserNotExistsException {
        String startDate = "2020-01-01";
        String finalDate = "2020-01-31";
        Integer id = 1;
        User u = createUser();
        List<InvoiceUserAndDate> list = new ArrayList<>();
        when(this.userRepository.getById(id)).thenReturn(null);
        this.invoiceService.getInvoicesByUserByDate(RangeDate.builder().idUser(id).dateFrom(format.parse(startDate)).dateTo(format.parse(finalDate)).build());
    }
}
