package com.utn.PhoneLines.service;


import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.model.City;
import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.UserType;
import com.utn.PhoneLines.model.dto.LoginInput;
import javax.xml.bind.ValidationException;
import com.utn.PhoneLines.model.dto.UpdateUserDto;
import java.util.Optional;
import com.utn.PhoneLines.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static com.utn.PhoneLines.model.enums.UserTypeEnum.CLIENT;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;
  // @Mock
 //   private CityService cityService;

    @Before
    public void setUp() {
        initMocks(this);
        this.userService = new UserService(userRepository);

    }

    @Test
    public void getUserByIdOkTest() throws UserNotExistsException {
        Integer id = 1;
        User user = createUser();
        when(this.userRepository.getById(id)).thenReturn(user);
        User test = this.userService.getById(id);
        Assert.assertEquals(user, test);

    }

    @Test
    public void deleteTest() throws UserNotExistsException {
        Integer id = 1;
        doNothing().when(this.userRepository).delete(id);
        this.userService.delete(id);
    }
    @Test
    public void UpdateClientOk() throws UserNotExistsException {

        UpdateUserDto dto = UpdateUserDto.builder()
                .idcity(1)
                .name("rocio1")
                .lastName("gocella1")
                .userName("rocio123")
                .build();

        User u = createUser();

        User updateU = User.builder()
                .idUser(1)
                .name(dto.getName())
                .lastName(dto.getLastName())
                .city(new City())
                .userName(dto.getUserName())
                .password("123")
                .userType(new UserType(11, CLIENT))
                .active(true)
                .dni("32111000")
                .build();

        when(this.userRepository.findById(1)).thenReturn(Optional.ofNullable(u));
        User testUser = userService.update(1, dto);
    }
    @Test
    public void testgetByUserNameAndPassword() throws UserNotExistsException, ValidationException {
        LoginInput dto = new LoginInput();
        dto.setUserName("mariagomez");
        dto.setPassword("123");

        User u = User.builder()
                .idUser(1)
                .name("n")
                .lastName("nn")
                .city(new City())
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .userType(new UserType(11, CLIENT))
                .active(true)
                .dni("32111000")
                .build();

        LoginInput dto2 = new LoginInput();
        dto2.setUserName("user");
        dto2.setPassword("pwd");


        when(this.userRepository.getByUserNameAndPassword("user", "pwd")).thenReturn(u);
        User returnedUser = this.userService.getByUserNameAndPassword(dto2);
        Assert.assertEquals(u.getUserName(), returnedUser.getUserName());
        Assert.assertEquals(u.getPassword(), returnedUser.getPassword());

        verify(userRepository, times(1)).getByUserNameAndPassword("user","pwd");
    }

    @Test(expected = UserNotExistsException.class)
    public void testExceptiongetByUserNameAndPassword() throws UserNotExistsException, ValidationException {
        LoginInput dto3 = new LoginInput();
        dto3.setUserName("user");
        dto3.setPassword("pwd");
        when(this.userRepository.getByUserNameAndPassword("user", "pwd")).thenReturn((User) null);
        this.userService.getByUserNameAndPassword(dto3);
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
