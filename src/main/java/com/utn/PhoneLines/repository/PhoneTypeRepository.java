package com.utn.PhoneLines.repository;

import com.utn.PhoneLines.model.PhoneType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneTypeRepository extends JpaRepository<PhoneType,Integer> {
    @Query(value = "SELECT * FROM phonetypes WHERE id_phone_type = ?1",nativeQuery = true)
    PhoneType getById(Integer i);
}
