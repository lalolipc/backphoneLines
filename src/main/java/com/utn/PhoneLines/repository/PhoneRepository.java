package com.utn.PhoneLines.repository;

import com.utn.PhoneLines.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Integer> {


    @Query(value = "SELECT * FROM phones WHERE number = ?1",nativeQuery = true)
    Phone findByNumber(String phoneNumber);

    @Query(value = "SELECT * FROM phones WHERE id_phone = ?1",nativeQuery = true)
    Phone getById(Integer id);

}
