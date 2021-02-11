package com.utn.PhoneLines.service;

import com.utn.PhoneLines.exceptions.UserException;
import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.model.Invoice;
import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.dto.RangeDate;
import com.utn.PhoneLines.projection.InvoiceUserAndDate;
import com.utn.PhoneLines.repository.InvoiceRepository;
import com.utn.PhoneLines.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, UserRepository userRepository) {
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
    }


    public ResponseEntity<List<InvoiceUserAndDate>> getInvoicesByUserByDate(RangeDate rangeDate) throws UserNotExistsException {
        if(this.userRepository.getById(rangeDate.getIdUser())==null){
            throw new UserNotExistsException();
        }
        List<InvoiceUserAndDate> bills = invoiceRepository.getReportInvoicesByUserByDate(rangeDate.getIdUser(), rangeDate.getDateFrom(), rangeDate.getDateTo());

        if (!bills.isEmpty()) {
            return ResponseEntity.ok(bills);

        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }
    }

    public ResponseEntity<List<Invoice>> getBillsByIdUser(Integer idUser) throws UserException {

        User u = new User();
        if((u = this.userRepository.getById(idUser)) == null){
            return (ResponseEntity<List<Invoice>>) Optional.ofNullable(null).orElseThrow(() -> new UserException("User not exists"));
        }
        List<Invoice> bills = new ArrayList<>();
        bills = this.invoiceRepository.getInvoicesByIdUser(idUser);
        if (!bills.isEmpty()) {
            return ResponseEntity.ok(bills);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }



}
