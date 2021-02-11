package com.utn.PhoneLines.controller.web;

import com.utn.PhoneLines.controller.CallController;
import com.utn.PhoneLines.controller.InvoiceController;
import com.utn.PhoneLines.controller.UserController;
import com.utn.PhoneLines.exceptions.PhoneNotExistsException;
import com.utn.PhoneLines.exceptions.UserException;
import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.model.*;
import com.utn.PhoneLines.model.dto.CallInfraestructure;
import com.utn.PhoneLines.model.dto.RangeDate;
import com.utn.PhoneLines.projection.CallsClient;
import com.utn.PhoneLines.projection.CallsClientTop;
import com.utn.PhoneLines.projection.InvoiceUserAndDate;
import com.utn.PhoneLines.session.SessionManager;
import com.utn.PhoneLines.utils.locationUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.utn.PhoneLines.exceptions.ValidationException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserController userController;
    private final CallController callController;
    private final InvoiceController invoiceController;
    private final SessionManager sessionManager;

    @Autowired
    public ApiController(final UserController userController, CallController callController, InvoiceController invoiceController, final SessionManager sessionManager){
        this.userController = userController;
        this.callController = callController;
        this.invoiceController = invoiceController;
        this.sessionManager = sessionManager;
    }

    @GetMapping("/")
    public ResponseEntity<User> getInfo(@RequestHeader("Authorization") String sessionToken) throws UserException {
        User currentUser = getCurrentUser(sessionToken);

        return ResponseEntity.ok(currentUser);

    }


    //funciona bien
    @GetMapping( "/calls/between-dates/{startDate}/{finalDate}")
    public ResponseEntity<List<CallsClient>> getCallsBtwDates(@RequestHeader("Authorization") String sessionToken,
                                                              @PathVariable(value = "startDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String startDate,
                                                              @PathVariable(value = "finalDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String finalDate)
            throws UserException, UserNotExistsException {

        User currentUser = getCurrentUser(sessionToken);
        return this.callController.getCallsOfUserByDate(new RangeDate(currentUser.getIdUser(), Date.valueOf(startDate),Date.valueOf(finalDate)));

    }



    @GetMapping( "/invoices/dates/{startDate}/{finalDate}")
    public ResponseEntity<List<InvoiceUserAndDate>> getInvoicesBtwDates(@RequestHeader("Authorization") String sessionToken,
                                                                        @PathVariable(value = "startDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String startDate,
                                                                        @PathVariable(value = "finalDate", required = true) @DateTimeFormat(pattern = "YYYY-MM-DD") String finalDate)
            throws UserException, UserNotExistsException {

        User currentUser = getCurrentUser(sessionToken);
        return this.invoiceController.getInvoicesBtwDates(new RangeDate(currentUser.getIdUser(), Date.valueOf(startDate),Date.valueOf(finalDate)));

    }

    @GetMapping( "/calls/top10Destinations")
    public ResponseEntity<List<CallsClientTop>> getTopDestination(@RequestHeader("Authorization") String sessionToken)
            throws UserException, UserNotExistsException {

        User currentUser = getCurrentUser(sessionToken);
        return this.callController.getTopDestination(currentUser);

    }

    private User getCurrentUser(String sessionToken) throws UserException {

        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken))
                .orElseThrow(() -> new UserException("User not Logged"));
    }


}
