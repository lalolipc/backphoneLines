package com.utn.PhoneLines.controller.web;

import com.utn.PhoneLines.controller.*;
import com.utn.PhoneLines.exceptions.*;
import com.utn.PhoneLines.model.*;
import com.utn.PhoneLines.model.dto.PhoneDto;
import com.utn.PhoneLines.model.dto.UpdatePhoneDto;
import com.utn.PhoneLines.model.dto.UpdateUserDto;
import com.utn.PhoneLines.projection.CallClientOffice;
import com.utn.PhoneLines.exceptions.ValidationException;
import com.utn.PhoneLines.session.SessionManager;
import com.utn.PhoneLines.utils.locationUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/backoffice")
public class BackofficeController {

    CallController callController;
    SessionManager sessionManager;
    InvoiceController invoiceController;
    UserController userController;
    PhoneController phoneController;
    RateController rateController;
    @Autowired
    public BackofficeController(CallController callController, SessionManager sessionManager, InvoiceController invoiceController, UserController userController, PhoneController phoneController, RateController rateController) {
        this.callController = callController;
        this.sessionManager = sessionManager;
        this.invoiceController = invoiceController;
        this.userController = userController;
        this.phoneController = phoneController;
        this.rateController = rateController;
    }



    //ok
    @GetMapping( "/calls/{idUser}")
    public ResponseEntity<List<CallClientOffice>> getCallsByUserBackoffice(@RequestHeader("Authorization") String sessionToken,
                                                                           @PathVariable(value = "idUser", required = true) Integer idUser)
            throws  UserNotExistsException {
        return this.callController.getCallsByUserBackoffice(idUser);

    }

    // MANEJO DE CLIENTES
//ok
    @GetMapping("/users/{idUser}")
    public ResponseEntity<User> getUserById (@RequestHeader("Authorization") String sessionToken,
                                             @PathVariable(value = "idUser", required = true)Integer idUser) throws UserException, UserNotExistsException {
        getCurrentUser(sessionToken);
        return this.userController.getUserById(idUser);
    }

    //ok
    @DeleteMapping("/users/{idUser}")
    public ResponseEntity deleteUser(@RequestHeader("Authorization") String sessionToken,
                                     @PathVariable(value = "idUser", required = true) Integer idUser) throws UserException, UserNotExistsException {
        getCurrentUser(sessionToken);
        this.userController.delete(idUser);
        return ResponseEntity.ok().build();
    }
//ok

    @PutMapping("/users/{idUser}")
    public ResponseEntity<User> updateClient(@RequestHeader("Authorization") String sessionToken,
                                             @PathVariable(value = "idUser", required = true) Integer idUser,
                                             @RequestBody UpdateUserDto updateUserDto) throws UserException,UserNotExistsException {
        getCurrentUser(sessionToken);

        return this.userController.update(idUser, updateUserDto);
    }

    //ALTA, BAJA y SUSPENCION DE LINEAS
//ok
    @PostMapping("/phone-lines")
    public ResponseEntity addPhoneLine(@RequestHeader("Authorization") String sessionToken,
                                       @RequestBody PhoneDto phone)throws UserException,ValidationException,PhoneAlreadyExistException  {
        getCurrentUser(sessionToken);
        ResponseEntity responseEntity;

        try {
            URI uri = locationUri.getLocation(this.phoneController.addPhone(phone).getIdPhone());
            responseEntity = ResponseEntity.created(uri).build();
        } catch (ValidationException | PhoneAlreadyExistException e) {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return responseEntity;


    }
    //ok
    @PutMapping("/changestatus/")
    public ResponseEntity disablePhoneLine (@RequestHeader("Authorization") String sessionToken,
                                            @RequestBody UpdatePhoneDto updatePhoneDto)throws UserException, UserException, PhoneNotExistsException {

        getCurrentUser(sessionToken);
        this.phoneController.changeStatus(updatePhoneDto);
        return ResponseEntity.ok().build();
    }



    //ok
    @DeleteMapping("/phone-lines/{idPhoneLine}")
    public ResponseEntity deletePhoneLine (@RequestHeader("Authorization") String sessionToken,
                                           @PathVariable(value = "idPhoneLine", required = true) Integer idPhoneLine) throws PhoneNotExistsException, UserException {


        getCurrentUser(sessionToken);
        this.phoneController.delete(idPhoneLine);
        return ResponseEntity.ok().build();
    }


    //ok
    private User getCurrentUser(String sessionToken) throws UserException {

        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken))
                .orElseThrow(() -> new UserException("User not Logged"));
    }

    @GetMapping("/rates")
    public ResponseEntity<List<Rate>> getRates(@RequestHeader("Authorization") String sessionToken) throws UserException {
        getCurrentUser(sessionToken);

        return this.rateController.getRates();
    }

    @GetMapping("/Rates/from={idCityFrom}/to={idCityTo}")
    public ResponseEntity<Rate> getRatesbyCity(@RequestHeader("Authorization") String sessionToken,
                                            @PathVariable(value = "idCityFrom", required = true) Integer idCityFrom,
                                            @PathVariable(value = "idCityTo", required = true) Integer idCityTo) throws UserException, ResourceNotExistException {
        getCurrentUser(sessionToken);

        return this.rateController.getRatesbyCity(idCityFrom, idCityTo);
    }


//nohacer pero guardar
    /*

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestHeader("Authorization") String sessionToken, @RequestBody User user) throws UserException {
        getCurrentUser(sessionToken);
        return this.userController.addUser(user);
    }
*/
}
