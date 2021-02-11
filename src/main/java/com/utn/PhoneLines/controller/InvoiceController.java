package com.utn.PhoneLines.controller;

import com.utn.PhoneLines.exceptions.UserException;
import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.model.Call;
import com.utn.PhoneLines.model.Invoice;
import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.dto.RangeDate;
import com.utn.PhoneLines.projection.CallsClient;
import com.utn.PhoneLines.projection.InvoiceUserAndDate;
import com.utn.PhoneLines.service.InvoiceService;
import com.utn.PhoneLines.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final SessionManager sessionManager;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, SessionManager sessionManager) {
        this.invoiceService = invoiceService;
        this.sessionManager = sessionManager;
    }


    public ResponseEntity<List<InvoiceUserAndDate>> getInvoicesBtwDates(RangeDate rangeDate) throws UserNotExistsException {

        return invoiceService.getInvoicesByUserByDate(rangeDate);

    }



    public ResponseEntity<List<Invoice>> findAll(Integer id) throws UserNotExistsException, UserException {

        ResponseEntity<List<Invoice>> listInvoices = invoiceService.getBillsByIdUser(id);
        return listInvoices.getBody().size()>0 ? listInvoices : ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    private User getCurrentUser(String sessionToken) throws UserException {

        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken))
                .orElseThrow(() -> new UserException("User not Logged"));
    }
}
