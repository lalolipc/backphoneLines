package com.utn.PhoneLines.service;

import com.utn.PhoneLines.exceptions.UserNotExistsException;
import com.utn.PhoneLines.model.User;
import com.utn.PhoneLines.model.UserType;
import com.utn.PhoneLines.model.dto.LoginInput;
import com.utn.PhoneLines.model.dto.UpdateUserDto;
import com.utn.PhoneLines.model.enums.UserTypeEnum;
import com.utn.PhoneLines.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
//ok

    public User getById(Integer idUser) throws UserNotExistsException {

        User user = this.userRepository.getById(idUser);

        return Optional.ofNullable(user).orElseThrow(() -> new UserNotExistsException());
    }

    public void delete(Integer idUser) throws UserNotExistsException {


        userRepository.delete(idUser);

    }

    public User update(Integer idUser, UpdateUserDto userDto) throws UserNotExistsException {

        User beforeUser = this.userRepository.findById(idUser).get();
        beforeUser.setName(userDto.getName());
        beforeUser.setLastName(userDto.getLastName());
       return this.userRepository.save(beforeUser);


    }


    public User getByUserNameAndPassword(LoginInput loginInput) throws UserNotExistsException, ValidationException {
        if ((loginInput.getUserName() != null) && (loginInput.getPassword() != null)) {
            User user= userRepository.getByUserNameAndPassword(loginInput.getUserName(), loginInput.getPassword());
            return Optional.ofNullable(user).orElseThrow(() -> new UserNotExistsException());
        } else {
            throw new ValidationException("username and password must have a value");
        }
    }



}
