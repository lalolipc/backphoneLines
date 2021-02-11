package com.utn.PhoneLines.repository;

import com.utn.PhoneLines.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    @Query(value = "SELECT * FROM users WHERE name = ?1",nativeQuery = true)
    List<User> findByName();
    @Query(value = "SELECT * FROM users WHERE id_user = ?1",nativeQuery = true)
    User getById(Integer idUser);

    @Query(value = "SELECT * FROM users WHERE user_name= ?1 AND password = ?2", nativeQuery = true)
    User getByUserNameAndPassword(String userName, String password);

   @Query(value = "update users u set u.active = false where u.id_user = ?1", nativeQuery = true)
    void delete(Integer idUser);
}
