package com.utn.PhoneLines.controller;

import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.model.City;
import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.UserType;
import com.utn.PhoneLines.model.dto.LoginInput;
import com.utn.PhoneLines.model.dto.NewUserDTO;
import com.utn.PhoneLines.model.dto.UpdateUserDto;
import com.utn.PhoneLines.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static com.utn.PhoneLines.model.enums.UserTypeEnum.CLIENT;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserControllerTest {
//@Mock crea un simulacro.
// @InjectMocks crea una instancia de la clase e inyecta las simulaciones creadas con las anotaciones
    @Mock
    UserService userServiceMockito;
    @InjectMocks
    UserController userController;

    @Before
    public void setUp(){initMocks(this);}





    @Test
    public void getUserByIdOkTest() throws UserNotExistsException {
        Integer id = 1;
        User user = createUser();
        when(this.userServiceMockito.getById(id)).thenReturn(user);
        ResponseEntity<User> response = this.userController.getUserById(id);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
       // Assert.assertEquals(user, test);


           }

    @Test
    public void updateTestOk() throws UserNotExistsException {
        Integer id = 1;
        UpdateUserDto dto = UpdateUserDto.builder()
                .idcity(1)
                .name("rocio1")
                .lastName("gocella1")
                .userName("rocio123")
                .build();
        User user = createUser();
        when(this.userServiceMockito.update(id,dto)).thenReturn(user);
        ResponseEntity<User> response = this.userController.update(id,dto);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    private User createUser(){
        return User.builder()
                .name("rocio")
                .lastName("gocella")
                .city(new City())
                .userName("rociogocella")
                .password("123")
                .userType(new UserType(11, CLIENT))
                .active(true)
                .dni("32111000")
                .idUser(1)
                .build();
    }

}
